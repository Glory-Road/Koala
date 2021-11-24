package com.lvtu.koala.ui.record

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.databinding.FragmentBankListBinding
import com.lvtu.koala.ui.add.BankActivity
import io.reactivex.android.schedulers.AndroidSchedulers

class BankListFragment : Fragment() {
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentBankListBinding? = null

    private val mAdapter by lazy { BankAdapter() }
    private val mViewModel by lazy { ViewModelProvider(this).get(BankListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBankListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initSubscribe()
    }

    private fun initView() {
        mBinding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.recycleView.adapter = mAdapter
        mAdapter.setEmptyView(R.layout.empty_layout)
    }

    private fun initListener() {
        mAdapter.setOnItemClickListener{ adapter, view, position ->
            val bank = mAdapter.getItem(position)
            BankActivity.start(requireContext(), bank.id)
        }
    }

    @SuppressLint("CheckResult")
    private fun initSubscribe() {
        mViewModel.bankList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.w(TAG, "initSubscribe: $it)")
                mAdapter.setNewData(it as MutableList<Bank>?)
            }

        mViewModel.getBankList()
    }

    companion object{
        private const val TAG = "BankListFragment"
    }
}