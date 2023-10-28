package com.tummoc.tummoc.callback

interface RItemListener<T> {
    fun onItemClick(t: T, position: Int, action: Int)
}