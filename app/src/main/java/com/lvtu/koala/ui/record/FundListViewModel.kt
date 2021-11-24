package com.lvtu.koala.ui.record

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.FundRepository
import com.lvtu.koala.data.domain.Fund
import io.reactivex.Observable
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

    companion object{
        private const val TAG = "BankListViewModel"
    }
}