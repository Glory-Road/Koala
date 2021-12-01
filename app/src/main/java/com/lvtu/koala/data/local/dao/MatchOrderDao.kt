package com.lvtu.koala.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lvtu.koala.data.local.entity.MatchOrderEntity
import io.reactivex.Single

@Dao
interface MatchOrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMatchOrder(matchOrder: MatchOrderEntity)

    @Query("SELECT * from MatchOrderEntity where bank_id = :bankId")
    fun getMatchOrder(bankId: Int): Single<MatchOrderEntity>
}