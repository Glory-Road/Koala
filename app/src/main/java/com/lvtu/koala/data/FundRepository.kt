package com.lvtu.koala.data

import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.local.KoalaDatabase
import com.lvtu.koala.data.maper.toDomain
import com.lvtu.koala.data.maper.toEntity
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class FundRepository() {
    private val dao = KoalaDatabase.dataBase.getFundDao()

    fun addFund(fund: Fund) {
        dao.insertFund(fund.toEntity())
    }

    fun updateFund(fund: Fund) {
        dao.updateFund(fund.toEntity())
    }

    fun getFund(fundId: Int): Single<Fund> {
        return dao.getFund(fundId).map {
            it.toDomain()
        }
    }

    fun getFundList(): Flowable<List<Fund>> {
        return dao.getFundList().map {
            it.map { it.toDomain() }
        }
    }
}