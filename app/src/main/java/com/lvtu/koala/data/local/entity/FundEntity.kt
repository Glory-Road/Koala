package com.lvtu.koala.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FundEntity")
data class FundEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "fund_name") val fundName: String,
    @ColumnInfo(name = "mount") val mount: Int,
    @ColumnInfo(name = "out_price") val outPrice: Float,
    @ColumnInfo(name = "register_count") val registerCount: Int,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "plan_mount") val planMount: Int,
    @ColumnInfo(name = "un_plan_mount") val unPlanMount: Int,
    @ColumnInfo(name = "bank_card") val bankCard: String,
    @ColumnInfo(name = "wechat") val wechat: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "bank_id") val bankId: Int,
)
