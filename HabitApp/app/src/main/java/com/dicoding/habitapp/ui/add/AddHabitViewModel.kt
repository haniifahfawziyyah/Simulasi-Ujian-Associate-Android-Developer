package com.dicoding.habitapp.ui.add

import androidx.lifecycle.ViewModel
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.data.HabitRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddHabitViewModel(private val habitRepository: HabitRepository) : ViewModel() {
    fun saveHabit(habit: Habit) {
        CoroutineScope(Dispatchers.IO).launch {
            habitRepository.insertHabit(habit)

        }
    }
}