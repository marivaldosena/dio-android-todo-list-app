package com.github.marivaldosena.diotodolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.marivaldosena.diotodolist.databinding.ActivityAddTodoBinding
import com.github.marivaldosena.diotodolist.datasource.TaskDataSource
import com.github.marivaldosena.diotodolist.extensions.format
import com.github.marivaldosena.diotodolist.extensions.text
import com.github.marivaldosena.diotodolist.model.Todo
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        insertListenerToDateInput()
        insertListenerToTimeInput()
        insertListenerToCancelButton()
        insertListenerToAddTodoButton()
    }

    private fun insertListenerToAddTodoButton() {
        binding.buttonAddTodo.setOnClickListener {
            with(binding) {
                val todo = Todo(
                    title = todoTitle.text,
                    date = todoDate.text,
                    hour = todoTime.text
                )

                TaskDataSource.insertTodo(todo)

                Log.e(this::class.toString(), "Adding todo: ${todo.title}")
            }
        }
    }

    private fun insertListenerToCancelButton() {
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun insertListenerToTimeInput() {
        binding.todoTime.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            with(timePicker) {
                addOnPositiveButtonClickListener {
                    val formattedMinute = if (minute in 0..9) "0$minute" else "$minute"
                    val formattedHour = if (hour in 0..9) "0$hour" else "$hour"

                    binding.todoTime.text = "$formattedHour:$formattedMinute"
                }

                show(supportFragmentManager, "TIME_PICKER_TAG")
            }
        }
    }

    private fun insertListenerToDateInput() {
        binding.todoDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.todoDate.text = Date(it + offset).format()
            }

            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }
}