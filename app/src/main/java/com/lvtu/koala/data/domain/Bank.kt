package com.lvtu.koala.data.domain

data class Bank (
    val id: Int,
    val bankName: String,
    val bankCode: String,
    val mount: Int,
    val bankPerson: String,
    val receivePerson: String,
    val bankAddress: String,
    val inPrice: Float,
    val frontMoneyReceive: Int,
    val registerCount: Int,
    val minMount: Int,
    val inDate: String,
)