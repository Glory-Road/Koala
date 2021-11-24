package com.lvtu.koala.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BankEntity")
data class BankEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "bank_name") val bankName: String,
    @ColumnInfo(name = "bank_code") val bankCode: String,
    @ColumnInfo(name = "mount") val mount: Int,
    @ColumnInfo(name = "bank_person") val bankPerson: String,
    @ColumnInfo(name = "receive_person") val receivePerson: String,
    @ColumnInfo(name = "bank_address") val bankAddress: String,
    @ColumnInfo(name = "in_price") val inPrice: Float,
    @ColumnInfo(name = "front_money_receive") val frontMoneyReceive: Int,
    @ColumnInfo(name = "register_count") val registerCount: Int,
    @ColumnInfo(name = "min_mount") val minMount: Int,
    @ColumnInfo(name = "in_date") val inDate: String,
)
