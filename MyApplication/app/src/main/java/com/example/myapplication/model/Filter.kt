package com.example.myapplication.model

sealed class Filter {
    object None : Filter()
    class ByMediaType(val type: MediaItem.Type) : Filter()
}