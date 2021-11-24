package com.lvtu.koala.ui.record

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.databinding.FragmentFundListBinding
import com.lvtu.koala.ui.add.FundActivity
import io.reactivex.android.schedulers.AndroidSchedulers

class FundListFragment : Fragment() {
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentFundListBinding? = null

    private val mAdapter by lazy { FundAdapter() }
    private val mViewModel by lazy { ViewModelProvider(this).get(FundListViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFundListBinding.inflate(inflater, container, false)
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
        mAdapter.setOnItemClickListener{ dapter, view, position->
            val fund = mAdapter.getItem(position)
            FundActivity.start(requireContext(), fund.id)
        }
    }

    @SuppressLint("CheckResult")
    private fun initSubscribe() {
        mViewModel.fundList
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.w(TAG, "initSubscribe: $it)")
                mAdapter.setNewData(it as MutableList<Fund>?)
            }

        mViewModel.getFundList()
    }

    companion object{
        private const val TAG = "BankListFragment"
    }
}