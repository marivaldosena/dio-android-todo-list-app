package com.github.marivaldosena.diotodolist.datasource

import com.github.marivaldosena.diotodolist.model.Todo

object TaskDataSource {
    private val list = arrayListOf<Todo>()

    fun getList() = list

    fun insertTodo(todo: Todo) {
        list.add(todo.copy(id = list.size + 1))
    }
}