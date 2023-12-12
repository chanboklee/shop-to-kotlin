package com.lee.shop.util

fun fail(message: String): Nothing{
    throw IllegalArgumentException(message)
}