package com.fernando.aplicacionlistadetareas

data class Task(val name: String, val category: TaskCategory, var isSelected:Boolean = false) {

}