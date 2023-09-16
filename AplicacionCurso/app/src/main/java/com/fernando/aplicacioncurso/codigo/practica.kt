package com.fernando.aplicacioncurso.codigo

fun main(){

    val lista = arrayListOf<Int>(25,50,50)

    val lista1 = listOf<Int>(100,240,430)

    println(lista)

    println(lista1)


    for (punto in lista.indices){
        println("Estamos en la posicion $punto")
    }

}