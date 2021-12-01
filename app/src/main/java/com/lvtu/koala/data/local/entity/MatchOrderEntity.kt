package com.lvtu.koala.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MatchOrderEntity")
data class MatchOrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "bank_id") val bankId: Int,
    @ColumnInfo(name = "funds") val funds: String,
)
