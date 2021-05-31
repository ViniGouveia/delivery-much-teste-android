package com.example.delivery_much_teste_android.shared.extensions

/**
 * @author Vinicius Gouveia on 29/05/2021
 */

fun String.Companion.emptyString() = EMPTY_STRING

fun String.capitalize(): String {
    return this[FIRST_INDEX].toUpperCase() + this.substring(SECOND_INDEX)
}

private const val EMPTY_STRING = ""
private const val FIRST_INDEX = 0
private const val SECOND_INDEX = 1
