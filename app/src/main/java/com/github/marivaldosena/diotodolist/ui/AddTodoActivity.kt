package com.github.marivaldosena.diotodolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.marivaldosena.diotodolist.databinding.ActivityAddTodoBinding
import com.github.marivaldosena.diotodolist.extensions.format
import com.github.marivaldosena.diotodolist.extensions.text
import com.google.android.material.datepicker.MaterialDatePicker
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