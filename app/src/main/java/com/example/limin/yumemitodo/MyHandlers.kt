package com.example.limin.yumemitodo

import android.view.View



class MyHandlers {
    internal fun onClickCheckBox(view:View,data:TodoItem) {
        if (data.state == TaskState.Done){
            data.state = TaskState.NotDone
        }
        else {
            data.state = TaskState.Done
        }
    }
}
