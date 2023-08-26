package com.fernando.aplicacionlistadetareas

sealed class TaskCategory {
    object Personal : TaskCategory()

    object Business: TaskCategory()
    object Other: TaskCategory()
}