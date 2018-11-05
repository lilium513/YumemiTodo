package com.example.limin.yumemitodo

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*

class TodoAdapter(val context: Context, list: List<TodoItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
     var mAllNames: List<TodoItem> = list
    private var mShowName: List<TodoItem> = list
    override fun getItemViewType(position: Int): Int {
        return position
    }
    fun getShowCount(): Int{
        return mShowName.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {

        (p0 as RowViewHolder).row.todoItem.isChecked = (mShowName[position].state == TaskState.Done)

        (p0).row.todoItem.text = mShowName[position].name

        if (mShowName[position].state == TaskState.Done){
            (p0).row.todoItem.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        else{
            (p0).row.todoItem.paintFlags = 0
        }


        (p0).row.todoItem.setOnClickListener {
            if (mShowName[position].state == TaskState.Done){
                mShowName[position].state = TaskState.NotDone
            }
            else if (mShowName[position].state == TaskState.NotDone){
                mShowName[position].state = TaskState.Done
            }

            notifyDataSetChanged()

        }

    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder { //チェックが入ったところだけを表示するように制御
        return RowViewHolder(layoutInflater.inflate(R.layout.row, viewGroup, false))


    }


    class RowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var row = itemView.todoRow
        var text = itemView.todoRow.todoItem.text

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
    fun checkAllItem(){
        mShowName.map {
            it.state =TaskState.Done
        }
        notifyDataSetChanged()

    }
    fun deleteFinished(){
        mAllNames = mAllNames.filter { item ->item.state == TaskState.NotDone }
        mShowName = mAllNames
        notifyDataSetChanged()
    }

}