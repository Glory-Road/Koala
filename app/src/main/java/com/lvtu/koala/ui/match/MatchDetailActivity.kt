package com.lvtu.koala.ui.match

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.databinding.ActivityMatchDetailBinding
import com.lvtu.koala.databinding.ActivityMatchHomeBinding
import com.lvtu.koala.databinding.ItemOrderFundsBinding
import com.lvtu.koala.ui.record.BankListViewModel
import com.lvtu.koala.ui.record.FundListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.zip.Inflater

class MatchDetailActivity : AppCompatActivity() {
    private var _binding: ActivityMatchDetailBinding? = null
    private val mBinding: ActivityMatchDetailBinding
        get() = _binding!!
    private val mBankViewModel by lazy { ViewModelProvider(this).get(BankListViewModel::class.java) }
    private val mFundViewModel by lazy { ViewModelProvider(this).get(FundListViewModel::class.java) }
    private val mMatchViewModel by lazy { ViewModelProvider(this).get(MatchViewModel::class.java) }
    private val bankId by lazy { intent.getIntExtra(BOOK_ID, 0) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initSubscribe()
    }

    private fun initSubscribe() {
        mBankViewModel.bank
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { bindData(it) }

        mFundViewModel.fundList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binFund(it)
            }

        mBankViewModel.getBank(bankId)
        mFundViewModel.getFundListByBank(bankId)
    }

    private fun bindData(bank: Bank) {
        mBinding.bankName.text = "行口：${bank.bankName}"


    }

    private fun binFund(funds: List<Fund>) {
        Log.w("WNQ", "binFund: ${funds.size}")
        funds.forEach {
            val fundItem = ItemOrderFundsBinding.inflate(layoutInflater, mBinding.fundLayout, false)
            fundItem.funds.text = "${it.fundName}：${it.mount}万"
            mBinding.fundLayout.addView(fundItem.root)
        }
    }

    companion object{
        private const val BOOK_ID = "book_id"

        fun start(context: Context, bankId: Int) {
            context.startActivity(Intent(context, MatchDetailActivity::class.java).apply {
                putExtra(BOOK_ID, bankId)
            })
        }
    }
}