package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var title: TextInputEditText
    private lateinit var desc: TextInputEditText
    private lateinit var dueDate: TextInputEditText
    private lateinit var btnDelete: Button
    private lateinit var detailTaskViewModel: DetailTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        title = findViewById(R.id.detail_ed_title)
        desc = findViewById(R.id.detail_ed_description)
        dueDate = findViewById(R.id.detail_ed_due_date)
        btnDelete = findViewById(R.id.btn_delete_task)

        detailTaskViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(this)
        )[DetailTaskViewModel::class.java]

        detailTaskViewModel.setTaskId(intent.getIntExtra(TASK_ID, 0))

        detailTaskViewModel.task.observe(this) { task ->
            if (task != null) {
                title.setText(task.title)
                desc.setText(task.description)
                dueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        }

        btnDelete.setOnClickListener {
            detailTaskViewModel.deleteTask()
            finish()
        }

    }
}