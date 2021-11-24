package com.lvtu.koala.data.local.dao

import androidx.room.*
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.local.entity.FundEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface FundDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFund(fund: FundEntity)

    @Update
    fun updateFund(fund: FundEntity)

    @Query("SELECT * from FundEntity where id = :fundId")
    fun getFund(fundId: Int): Single<FundEntity>

    @Query("SELECT * from FundEntity")
    fun getFundList(): Flowable<List<FundEntity>>
}