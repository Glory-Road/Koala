package com.lvtu.koala.data.domain

data class Match(
    val bankName: String,
    val mount: Int,
    val fundName: MutableList<String>,
)
