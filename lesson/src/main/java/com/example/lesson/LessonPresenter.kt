package com.example.lesson

import com.example.core.http.EntityCallback
import com.example.core.http.HttpClient.get
import com.example.core.utils.Utils.toast
import com.example.lesson.entity.Lesson
import java.lang.reflect.Type
import java.util.ArrayList
import com.google.gson.reflect.TypeToken

class LessonPresenter {
  companion object {
    const val LESSON_PATH = "lessons"
  }

  private var activity: LessonActivity? = null

  constructor(activity: LessonActivity?) {
    this.activity = activity
  }

  private var lessons: List<Lesson> = ArrayList()

  private val type: Type = object : TypeToken<List<Lesson?>?>() {}.getType()

  fun fetchData() {
    get(LESSON_PATH, type, object : EntityCallback<List<Lesson>> {
      override fun onSuccess(lessons: List<Lesson>) {
        this@LessonPresenter.lessons = lessons
        activity!!.runOnUiThread { activity!!.showResult(lessons) }
      }

      override fun onFailure(message: String?) {
        activity!!.runOnUiThread { toast(message!!) }
      }
    })
  }

  fun showPlayback() {
    val playbackLessons: MutableList<Lesson> = ArrayList()
    for (lesson in lessons) {
      if (lesson.getState() === Lesson.State.PLAYBACK) {
        playbackLessons.add(lesson)
      }
    }
    activity!!.showResult(playbackLessons)
  }
}