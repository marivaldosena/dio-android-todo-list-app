package com.github.marivaldosena.diotodolist.model

data class Todo(
    val title: String,
    val hour: String,
    val date: String,
    val id: Int = 0
)
