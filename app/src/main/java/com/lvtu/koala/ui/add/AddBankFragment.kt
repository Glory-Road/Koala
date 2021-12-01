package com.lvtu.koala.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.databinding.FragmentAddBankBinding
import com.lvtu.koala.ui.add.BankActivity.Companion.BANK_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddBankFragment : Fragment() {
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentAddBankBinding? = null

    private val mViewModel by lazy { ViewModelProvider(this).get(AddBankViewModel::class.java) }

    private val mBankId by lazy { arguments?.getInt(BANK_ID, 0) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBankBinding.inflate(inflater, container, false)
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
        mViewModel.bank
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe { bindData(it) }

        mBankId?.let {
            if (mBankId != 0) {
                mViewModel.getBank(it)
            }
        }

    }

    private fun initListener() {
        mBinding.etMount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val mount = mBinding.etMount.text.toString().trim()
                val inPrice = mBinding.etInPrice.text.toString().trim()
                val frontMoneyPresent = mBinding.etFrontMoneyPresent.text.toString().trim()
                if (mount.isEmpty() || mount.toInt() <= 0) {
                    mBinding.tvTotalProfit.text = ""
                    mBinding.tvReceivableFrontMoney.text = ""
                    return
                }

                if (inPrice.isEmpty() || inPrice.toFloat() <= 0.0f) {
                    mBinding.tvTotalProfit.text = ""
                } else {
                    val totalProfit = mount.toInt() * inPrice.toFloat()
                    mBinding.tvTotalProfit.text = "应收总结算金额：${totalProfit}元"
                }

                if (frontMoneyPresent.isEmpty() || frontMoneyPresent.toFloat() <= 0.0f) {
                    mBinding.tvReceivableFrontMoney.text = ""
                } else {
                    val receivableFrontMoney = mount.toInt() * frontMoneyPresent.toFloat()
                    mBinding.tvReceivableFrontMoney.text = "应收定金：${receivableFrontMoney}元"
                }
            }
        })

        mBinding.etFrontMoneyPresent.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val mount = mBinding.etMount.text.toString().trim()
                val frontMoneyPresent = mBinding.etFrontMoneyPresent.text.toString().trim()
                if (mount.isEmpty() || mount.toInt() <= 0 || frontMoneyPresent.isEmpty() || frontMoneyPresent.toFloat() <= 0) {
                    mBinding.tvReceivableFrontMoney.text = ""
                    return
                }

                val receivableFrontMoney = mount.toInt() * frontMoneyPresent.toFloat()
                mBinding.tvReceivableFrontMoney.text = "应收定金：${receivableFrontMoney}元"
            }
        })

        mBinding.etInPrice.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val mount = mBinding.etMount.text.toString().trim()
                val inPrice = mBinding.etInPrice.text.toString().trim()
                if (mount.isEmpty() || mount.toInt() <= 0 || inPrice.isEmpty() || inPrice.toFloat() <= 0.0f) {
                    mBinding.tvTotalProfit.text = ""
                    return
                }

                val totalProfit = mount.toInt() * inPrice.toFloat()
                mBinding.tvTotalProfit.text = "应收总结算金额：${totalProfit}元"
            }
        })

        mBinding.save.setOnClickListener {
            val id = mBankId?:0
            val bankName = mBinding.etBank.text.toString().trim()
            val bankCode = mBinding.etBankCode.text.toString().trim()
            val bankAddress = mBinding.etBankAddress.text.toString().trim()
            val mount = mBinding.etMount.text.toString().trim().toInt()
            val inPrice = mBinding.etInPrice.text.toString().trim().toFloat()
            val frontMoneyReceive = mBinding.etFrontMoneyReceive.text.toString().trim().toInt()
            val bankPerson = mBinding.etBankPerson.text.toString().trim()
            val receivePerson = mBinding.etReceiverPerson.text.toString().trim()
            val registerCount = mBinding.etRegisterCount.text.toString().trim().toInt()
            val minMount = mBinding.etMinMount.text.toString().trim().toInt()
            val inDate = mBinding.etInDate.text.toString().trim()
            val bank = Bank(id, bankName, bankCode, mount, bankPerson, receivePerson, bankAddress, inPrice, frontMoneyReceive, registerCount, minMount, inDate, 0)
            if (id > 0) {
                mViewModel.updateBank(bank)
            } else {
                mViewModel.addBank(bank)
            }
            requireActivity().finish()
        }
    }

    private fun initView() {

    }

    private fun bindData(bank: Bank) {
        mBinding.etBank.setText(bank.bankName)
        mBinding.etBankCode.setText(bank.bankCode)
        mBinding.etBankAddress.setText(bank.bankAddress)
        mBinding.etMount.setText(bank.mount.toString())
        mBinding.etInPrice.setText(bank.inPrice.toString())
        mBinding.etFrontMoneyReceive.setText(bank.frontMoneyReceive.toString())
        mBinding.etBankPerson.setText(bank.bankPerson)
        mBinding.etReceiverPerson.setText(bank.receivePerson)
        mBinding.etRegisterCount.setText(bank.registerCount.toString())
        mBinding.etMinMount.setText(bank.minMount.toString())
        mBinding.etInDate.setText(bank.inDate)
    }

    companion object{

        fun create(bankId: Int): AddBankFragment {
            val fragment = AddBankFragment().apply {
                arguments = Bundle().apply {
                    putInt(BANK_ID, bankId)
                }
            }
            return fragment
        }
    }
}