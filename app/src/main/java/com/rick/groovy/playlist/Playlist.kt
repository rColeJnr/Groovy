package com.rick.groovy.playlist

import com.rick.groovy.R

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    val image: Int = R.drawable.ic_launcher_foreground,
)
