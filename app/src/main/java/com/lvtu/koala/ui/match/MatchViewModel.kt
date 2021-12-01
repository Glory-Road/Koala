package com.lvtu.koala.ui.match

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lvtu.koala.data.BankRepository
import com.lvtu.koala.data.FundRepository
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.domain.MatchOrder
import com.lvtu.koala.data.maper.MatchOrderRepository

class MatchViewModel: ViewModel() {

    private val bankRepository = BankRepository()
    private val fundRepository = FundRepository()
    private val matchRepository = MatchOrderRepository()

    var fundList: List<Fund>? = null
    var bankList: List<Bank>? = null

    fun refresh(){
        getBankList()
        getFundList()
    }

    private fun getBankList() {
        bankRepository.getBankList().subscribe {
            Log.w(TAG, "getBankList: $it")
            bankList = it
        }
    }

    private fun getFundList() {
        fundRepository.getFundList().subscribe {
            Log.w(TAG, "getFundList: $it")
            fundList = it
        }
    }

    fun saveMatchOrder(matchOrder: MatchOrder) {
        matchRepository.addMatchOrder(matchOrder)
    }

    companion object{
        private const val TAG = "MatchViewModel"
    }
}