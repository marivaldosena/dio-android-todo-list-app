package com.github.marivaldosena.diotodolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.marivaldosena.diotodolist.databinding.ActivityAddTodoBinding
import com.github.marivaldosena.diotodolist.extensions.format
import com.github.marivaldosena.diotodolist.extensions.text
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
    }

    private fun insertListenerToTimeInput() {
        binding.todoTime.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            with(timePicker) {
                addOnPositiveButtonClickListener {
                    binding.todoTime.text = "$hour $minute"
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