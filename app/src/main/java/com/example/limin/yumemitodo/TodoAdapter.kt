package com.example.limin.yumemitodo

import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.limin.yumemitodo.databinding.RowBinding
import kotlinx.android.synthetic.main.header.view.*
import kotlinx.android.synthetic.main.row.view.*

class TodoAdapter(val context: Context, list: List<TodoItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var mAllNames: List<TodoItem> = list
    private var mShowName: List<TodoItem> = list


    class RowViewHolder(val binding: RowBinding): RecyclerView.ViewHolder(binding.root) { }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun getShowCount(): Int {
        return mShowName.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {

        val todoData = mShowName[position]
        val rowview = p0 as RowViewHolder
        rowview.binding.data = todoData

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder { //チェックが入ったところだけを表示するように制御

        val binding =RowBinding.inflate(layoutInflater,viewGroup,false)
        return TodoAdapter.RowViewHolder(binding)

    }


    override fun getItemCount(): Int {
        return mShowName.size
    }

    fun showAll() {
        mShowName = mAllNames
    }

    fun showActive() {
        mShowName = mAllNames.filter { item -> item.state == TaskState.NotDone }
    }

    fun showDeactive() {
        mShowName = mAllNames.filter { item -> item.state == TaskState.Done }
    }

    fun checkAllItem() {
        mShowName.map {
            it.state = TaskState.Done
        }

    }

    fun deleteFinished() {
        mAllNames = mAllNames.filter { item -> item.state == TaskState.NotDone }
        mShowName = mAllNames
    }

}