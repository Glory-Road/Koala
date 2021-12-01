package com.lvtu.koala.data.domain

data class Fund (
    val id: Int,
    val fundName: String,
    val mount: Int,
    val outPrice: Float,
    val registerCount: Int,
    var status: Int,
    val planMount: Int,
    val unPlanMount: Int,
    val bankCard: String,
    val wechat: String,
    val phone: String,
    var bankId: Int,
) {
    var checked: Boolean = false
}