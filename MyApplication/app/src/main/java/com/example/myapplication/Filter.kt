package com.example.myapplication

sealed class Filter {
    object None : Filter()
    class ByMediaType(val type: MediaItem.Type) : Filter()
}