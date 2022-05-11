package com.example.core

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  private val viewHashMap: HashMap<Int, View> = HashMap()

  protected fun <T : View> getView(id: Int): T {
    var view = viewHashMap[id]
    if (view == null) {
      view = itemView.findViewById(id)
      viewHashMap[id] = view
    }
    return view as T
  }

  protected fun setText(id: Int, text: String?) {
    (getView(id) as TextView).text = text
  }
}