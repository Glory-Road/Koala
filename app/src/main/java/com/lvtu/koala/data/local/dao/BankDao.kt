package com.lvtu.koala.data.local.dao

import androidx.room.*
import com.lvtu.koala.data.local.entity.BankEntity
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface BankDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBank(bank: BankEntity)

    @Update
    fun updateBank(bank: BankEntity)

    @Query("SELECT * from BankEntity")
    fun getBankList(): Flowable<List<BankEntity>>

    @Query("SELECT * from BankEntity where id = :bankId")
    fun getBank(bankId: Int): Single<BankEntity>
}