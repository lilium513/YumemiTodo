package com.example.limin.yumemitodo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.footer.view.*
import kotlinx.android.synthetic.main.header.view.*


private var recyclerView: RecyclerView? = null
private var recyclerViewAdapter: TodoAdapter? = null

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = todoList

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager


        var list: MutableList<TodoItem> = mutableListOf()

        recyclerViewAdapter = TodoAdapter(this, list)
        recyclerView?.adapter = recyclerViewAdapter


        header_included.add_button.setOnClickListener {
            var t = header_included.editText.text.toString()
            if (t.isNotEmpty()) {
                list.add(TodoItem(t))
                header_included.editText.text.clear()
                recyclerViewAdapter?.notifyDataSetChanged()

            }
        }
        header_included.check_all_button.setOnClickListener {
            recyclerViewAdapter?.checkAllItem()
            recyclerViewAdapter?.notifyDataSetChanged()
        }
        footer_included.show_active.setOnClickListener {
            recyclerViewAdapter?.showActive()
            recyclerViewAdapter?.notifyDataSetChanged()
        }
        footer_included.show_finished.setOnClickListener {
            recyclerViewAdapter?.showDeactive()
            recyclerViewAdapter?.notifyDataSetChanged()
        }
        footer_included.show_all.setOnClickListener {
            recyclerViewAdapter?.showAll()
            recyclerViewAdapter?.notifyDataSetChanged()
        }
        footer_included.delete_finished.setOnClickListener {
            recyclerViewAdapter?.deleteFinished()
            list = (recyclerViewAdapter?.mAllNames) as MutableList<TodoItem>
        }

    }

}
