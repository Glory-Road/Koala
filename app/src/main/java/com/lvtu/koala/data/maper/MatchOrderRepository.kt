package com.lvtu.koala.data.maper

import com.lvtu.koala.data.domain.MatchOrder
import com.lvtu.koala.data.local.KoalaDatabase
import io.reactivex.Single

class MatchOrderRepository() {
    private val dao = KoalaDatabase.dataBase.getMatchOrderDao()

    fun addMatchOrder(matchOrder: MatchOrder) {
        dao.insertMatchOrder(matchOrder.toEntity())
    }

//    fun updateBank(bank: Bank) {
//        dao.updateBank(bank.toEntity())
//    }
//
    fun getMatchOrder(bankId: Int): Single<MatchOrder> {
        return dao.getMatchOrder(bankId).map {
            it.toDomain()
        }
    }
//
//    fun getBankList(): Flowable<List<Bank>> {
//        return dao.getBankList().map {
//            it.map { it.toDomain() }
//        }
//    }
}