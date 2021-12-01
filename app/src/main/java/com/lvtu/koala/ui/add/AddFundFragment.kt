package com.lvtu.koala.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.databinding.FragmentAddFundBinding
import com.lvtu.koala.ui.add.FundActivity.Companion.FUND_ID
import io.reactivex.android.schedulers.AndroidSchedulers

class AddFundFragment : Fragment() {
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentAddFundBinding? = null
    private val mViewModel by lazy { ViewModelProvider(this).get(AddFundViewModel::class.java) }
    private val mFundId by lazy { arguments?.getInt(FUND_ID, 0) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFundBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initSubscribe()
    }

    @SuppressLint("CheckResult")
    private fun initSubscribe() {
        mViewModel.fund
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { bindData(it) }

        mFundId?.let {
            if (it > 0) {
                mViewModel.getFund(it)
            }
        }
    }

    private fun initListener() {
        mBinding.save.setOnClickListener {
            val id = mFundId ?: 0
            val fundName = mBinding.etFundName.text.toString().trim()
            val mount = mBinding.etFundMount.text.toString().trim().toInt()
            val outPrice = mBinding.etOutPrice.text.toString().trim().toFloat()
            val registerCount = mBinding.etRegisterCount.text.toString().trim().toInt()
            val status = 0
            val planMount = 0
            val unPlanMount = 0
            val bankCard = mBinding.etBankCard.text.toString()
            val wechat = mBinding.etWechat.text.toString()
            val phone = mBinding.etPhone.text.toString()
            val fund = Fund(id, fundName, mount, outPrice, registerCount, status, planMount, unPlanMount, bankCard, wechat, phone, -1)
            if (id > 0) {
                mViewModel.updateFund(fund)
            } else {
                mViewModel.addFund(fund)
            }
            requireActivity().finish()
        }
    }

    private fun initView() {


    }

    private fun bindData(fund: Fund) {
        mBinding.etFundName.setText(fund.fundName)
        mBinding.etFundMount.setText(fund.mount.toString())
        mBinding.etOutPrice.setText(fund.outPrice.toString())
        mBinding.etRegisterCount.setText(fund.registerCount.toString())
        mBinding.etBankCard.setText(fund.bankCard)
        mBinding.etWechat.setText(fund.wechat)
        mBinding.etPhone.setText(fund.phone)
    }

    companion object{

        fun create(fundId: Int): AddFundFragment {
            val fragment = AddFundFragment().apply {
                arguments = Bundle().apply {
                    putInt(FUND_ID, fundId)
                }
            }
            return fragment
        }
    }
}