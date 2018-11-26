package com.example.limin.yumemitodo

import java.io.Serializable

class TodoItem(name: String):Serializable{
    var name: String = name
    var state:TaskState = TaskState.NotDone
}