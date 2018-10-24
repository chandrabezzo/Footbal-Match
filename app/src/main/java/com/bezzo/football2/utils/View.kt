package com.bezzo.football2.utils

import android.view.View

fun View.show(){
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}