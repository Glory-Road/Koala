package com.lvtu.koala.ui.match

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.domain.MatchOrder
import com.lvtu.koala.databinding.ActivityMatchHomeBinding
import com.lvtu.koala.ui.match.adapter.MatchBankAdapter
import com.lvtu.koala.ui.match.adapter.MatchFundAdapter
import com.lvtu.koala.ui.record.BankListViewModel
import com.lvtu.koala.ui.record.FundListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class MatchHomeActivity : AppCompatActivity() {
    private var _binding: ActivityMatchHomeBinding? = null
    private val mBinding: ActivityMatchHomeBinding
        get() = _binding!!
    private val mBankAdapter by lazy { MatchBankAdapter() }
    private val mFundAdapter by lazy { MatchFundAdapter() }
    private val mBankViewModel by lazy { ViewModelProvider(this).get(BankListViewModel::class.java) }
    private val mFundViewModel by lazy { ViewModelProvider(this).get(FundListViewModel::class.java) }
    private val mMatchViewModel by lazy { ViewModelProvider(this).get(MatchViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMatchHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initData()
    }

    private fun initView() {
        mBinding.bankList.layoutManager = LinearLayoutManager(this)
        mBinding.bankList.adapter = mBankAdapter
        mBinding.bankList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBinding.fundList.layoutManager = LinearLayoutManager(this)
        mBinding.fundList.adapter = mFundAdapter
        mBinding.fundList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBankAdapter.setOnItemClickListener { adapter, view, position ->
            val old = mBankAdapter.data.find { it.checked }
            old?.let {
                val oldPosition = mBankAdapter.data.indexOf(old)
                it.checked = false
                mBankAdapter.notifyItemChanged(oldPosition)
            }
            val bank = mBankAdapter.getItem(position)
            bank.checked = bank.checked.not()
            mBankAdapter.notifyItemChanged(position)

            if (bank.status == 0) {
                Log.w(TAG, "initView: getUnMatchFundList")
                mFundViewModel.getUnMatchFundList()
            } else {
                mFundViewModel.getFundListByBank(bank.id)
            }
        }
        mFundAdapter.setSelectListener(object : MatchFundAdapter.SelectListener{
            override fun onSelectListener(selects: List<Fund>) {
                Log.w(TAG, "onSelectListener: ${selects.size}")
                mBinding.confirm.isSelected = selects.isNotEmpty()
            }
        })

        mBinding.confirm.setOnClickListener {
            if (it.isSelected) {
                Log.w(TAG, "initView: 敲定")
                launchMatch()
            }
        }
        mBinding.toOrder.setOnClickListener {
            val bank = mBankAdapter.data.find { it.checked }
            MatchDetailActivity.start(this, bank?.id?:0)
        }
    }

    private fun initData() {
        mBankViewModel.bankList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    it[0].checked = true
                    if (it[0].status == 1) {
                        mFundViewModel.getFundListByBank(it[0].id)
                    } else {
                        mFundViewModel.getFundList()
                    }
                }
                mBankAdapter.setNewData(it as MutableList<Bank>?)
            }
        mFundViewModel.fundList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.w(TAG, "initData: $it")
                mFundAdapter.setNewData(it as MutableList<Fund>?)
            }
        mBankViewModel.getBankList()
    }

    private fun launchMatch() {
        val bank = mBankAdapter.data.find { it.checked }
        val funds = mFundAdapter.selects
        bank?.let {
            val names = funds.map { it.fundName }.toMutableList()
            var fundIds = ""
            names.forEach {
                fundIds += "$it,"
            }
            val order = MatchOrder(0, it.id, fundIds.substring(0, fundIds.length-1))
            mMatchViewModel.saveMatchOrder(order)

            bank.status = 1
            mBankViewModel.updateBank(bank)

            funds.forEach {
                it.status = 1
                it.bankId = bank.id
                mFundViewModel.updateFund(it)
            }
        }
    }

    companion object{
        private const val TAG = "MatchHomeActivity"
        fun start(context: Context) {
            context.startActivity(Intent(context, MatchHomeActivity::class.java))
        }
    }
}