package com.lvtu.koala.data

import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.local.KoalaDatabase
import com.lvtu.koala.data.local.dao.BankDao
import com.lvtu.koala.data.local.entity.BankEntity
import com.lvtu.koala.data.maper.toDomain
import com.lvtu.koala.data.maper.toEntity
import io.reactivex.Flowable
import io.reactivex.Single

class BankRepository() {
    private val dao = KoalaDatabase.dataBase.getBankDao()

    fun addBank(bank: Bank) {
        dao.insertBank(bank.toEntity())
    }

    fun updateBank(bank: Bank) {
        dao.updateBank(bank.toEntity())
    }

    fun getBank(bankId: Int): Single<Bank> {
        return dao.getBank(bankId).map {
            it.toDomain()
        }
    }

    fun getBankList(): Flowable<List<Bank>> {
        return dao.getBankList().map {
            it.map { it.toDomain() }
        }
    }
}