package com.lvtu.koala.ui.add

import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.FundRepository
import com.lvtu.koala.data.domain.Fund
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class AddFundViewModel: ViewModel() {

    private val fundRepository = FundRepository()
    private val _fundList: BehaviorSubject<List<Fund>> = BehaviorSubject.create()
    private val _fund: BehaviorSubject<Fund> = BehaviorSubject.create()
    val fundList
        get() = _fundList

    val fund
        get() = _fund

    fun addFund(fund: Fund) {
        fundRepository.addFund(fund)
    }

    fun updateFund(fund: Fund) {
        fundRepository.updateFund(fund)
    }

    fun getFund(fundId: Int) {
        fundRepository.getFund(fundId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                _fund.onNext(it)
            }
            .subscribe()
    }

    fun getFundList() {
        fundRepository.getFundList()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _fundList.onNext(it)
            }
    }
}