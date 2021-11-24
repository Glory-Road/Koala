package com.lvtu.koala.ui.record

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.BankRepository
import com.lvtu.koala.data.domain.Bank
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

@SuppressLint("CheckResult")
class BankListViewModel : ViewModel() {

    private val bankRepository = BankRepository()
    val bankList: Observable<List<Bank>>
        get() = _bankList.hide()
    private val _bankList: BehaviorSubject<List<Bank>> = BehaviorSubject.create()

    fun getBankList() {
        bankRepository.getBankList().subscribe {
            Log.w(TAG, "getBankList: $it")
            _bankList.onNext(it)
        }
    }

    companion object{
        private const val TAG = "BankListViewModel"
    }
}