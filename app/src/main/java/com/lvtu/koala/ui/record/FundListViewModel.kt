package com.lvtu.koala.ui.record

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.FundRepository
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

@SuppressLint("CheckResult")
class FundListViewModel : ViewModel() {

    private val fundRepository = FundRepository()
    val fundList: Observable<List<Fund>>
        get() = _fundList.hide()
    private val _fundList: BehaviorSubject<List<Fund>> = BehaviorSubject.create()

    fun getFundList() {
        fundRepository.getFundList().subscribe {
            Log.w(TAG, "getFundList: $it")
            _fundList.onNext(it)
        }
    }

    fun getFundListByBank(bankId: Int) {
        fundRepository.getFundListByBank(bankId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Log.w(TAG, "getFundList: $it")
                _fundList.onNext(it)
            }
            .subscribe()
    }

    fun getUnMatchFundList() {
        fundRepository.getUnMatchFundList()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                Log.w(TAG, "getFundList: $it")
                _fundList.onNext(it)
            }
            .subscribe()
    }

    fun updateFund(fund: Fund) {
        fundRepository.updateFund(fund)
    }

    companion object{
        private const val TAG = "BankListViewModel"
    }
}