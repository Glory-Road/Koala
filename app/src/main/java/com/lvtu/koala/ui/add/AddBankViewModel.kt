package com.lvtu.koala.ui.add

import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.BankRepository
import com.lvtu.koala.data.domain.Bank
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.BehaviorSubject

class AddBankViewModel: ViewModel() {

    private val bankRepository = BankRepository()
    private val _bankList: BehaviorSubject<List<Bank>> = BehaviorSubject.create()
    private val _bank: BehaviorSubject<Bank> = BehaviorSubject.create()
    val bankList
        get() = _bankList
    val bank
        get() = _bank

    fun getBank(bankId: Int) {
        bankRepository.getBank(bankId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                _bank.onNext(it)
            }
            .subscribe()
    }

    fun addBank(bank: Bank) {
        bankRepository.addBank(bank)
    }

    fun updateBank(bank: Bank) {
        bankRepository.updateBank(bank)
    }
    fun getBankList() {
        bankRepository.getBankList()
            .subscribeOn(Schedulers.io())
            .subscribe {
                _bankList.onNext(it)
            }
    }
}