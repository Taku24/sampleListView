package com.example.sampledatabinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlin.coroutines.coroutineContext

class ListAdapter(context: Context): BaseAdapter() {

    // セクション
    private val ITEM_SECTION = 0

    // アイテム
    private val ITEM_NORMAL = 1

    // 表示するアイテム
    private val mItems = listOf("section", "item1-1", "item1-2", "item1-3",  "section", "item2-1", "item2-2", "item2-3")

    private val mContext = context

    // viewの設定を行う
    override fun getView(position: Int, view: View?, container: ViewGroup?): View {
        var itemView = view ?: View(mContext)
        when(getItemViewType(position)) {
            ITEM_SECTION -> {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_section, container, false)
                itemView.findViewById<TextView>(R.id.item_section_title).text = getItem(position)
            }
            ITEM_NORMAL -> {
                itemView = LayoutInflater.from(mContext).inflate(R.layout.item_normal, container, false)
                itemView.findViewById<TextView>(R.id.item_normal_title).text = getItem(position)
            }
        }
        return itemView
    }

    override fun getItem(position: Int): String {
        return mItems[position]
    }

    // アイテムの種類を設定
    override fun getItemViewType(position: Int): Int {
        return if (mItems[position] == "section") {
            ITEM_SECTION
        } else {
            ITEM_NORMAL
        }
    }

    // viewの個数を返す
    override fun getViewTypeCount(): Int {
        return 2
    }

    // アイテムIDを設定
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // セクションの数までを含めた個数を返す
    override fun getCount(): Int {
        return mItems.size
    }

}