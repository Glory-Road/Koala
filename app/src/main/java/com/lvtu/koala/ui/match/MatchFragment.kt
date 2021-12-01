package com.lvtu.koala.ui.match

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvtu.koala.data.domain.MatchOrder
import com.lvtu.koala.databinding.FragmentMatchBinding

class MatchFragment : Fragment() {
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentMatchBinding? = null
    private val mViewModel by lazy { ViewModelProvider(this).get(MatchViewModel::class.java) }
    private val mAdapter by lazy { MatchAdapter() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initSubscribe()
    }


    private fun initView() {
        mBinding.matchList.layoutManager = LinearLayoutManager(requireContext())
        mBinding.matchList.adapter = mAdapter
    }

    private fun initListener() {
        mBinding.launchMatch.setOnClickListener {
            MatchHomeActivity.start(requireContext())
//            launchMatch()
        }
    }


    private fun initSubscribe() {

    }

    override fun onResume() {
        super.onResume()
        mViewModel.refresh()
    }

    private fun launchMatch() {
        val banks = mViewModel.bankList?.toMutableList()
        banks?.sortBy {
            it.registerCount
        }
        val funds = mViewModel.fundList?.toMutableList()
        val matches = mutableListOf<MatchOrder>()
        //先匹配一对一
        Log.w(TAG, "匹配前 行口: ${banks?.size}-->$banks")
        Log.w(TAG, "匹配前 资方: ${funds?.size}-->$funds")
        banks?.let {
            for (bankIndex in banks.size - 1 downTo 0) {
                funds?.let {
                    for (fundIndex in funds.size - 1 downTo 0){
                        if (banks[bankIndex].registerCount == 1 && funds[fundIndex].registerCount == 1 && banks[bankIndex].mount == funds[fundIndex].mount) {
                            Log.w(TAG, "launchMatch: add")
//                            matches.add(MatchOrder(banks[bankIndex].bankName, banks[bankIndex].mount, mutableListOf(funds[fundIndex].fundName)))
                            banks.removeAt(bankIndex)
                            funds.removeAt(fundIndex)
                        }
                    }
                }
            }
        }
        Log.w(TAG, "第一轮匹配后 行口: ${banks?.size}-->$banks")
        Log.w(TAG, "第一轮匹配后 资方: ${funds?.size}-->$funds")
//        banks?.let {
//            for (bankIndex in banks.size - 1 downTo 0) {
//                val bank = banks[bankIndex]
//                funds?.let {
//                    for (fundIndex1 in funds.size - 1 downTo 0){
//                        val fund = funds[fundIndex1]
//                        val matchMount = bank.mount - fund.mount
//                        for (fundIndex2 in fundIndex1 downTo 0) {
//                            if (bank.registerCount == 2 && matchMount == funds[fundIndex2].mount) {
//                                Log.w(TAG, "launchMatch: add")
//                                val modFunds = mutableListOf<String>()
//                                modFunds.add(funds[fundIndex1].fundName)
//                                modFunds.add(funds[fundIndex2].fundName)
//                                matches.add(Match(banks[bankIndex].bankName, modFunds))
//                                banks.removeAt(bankIndex)
//                                funds.removeAt(fundIndex1)
//                                funds.removeAt(fundIndex2)
//                            }
//                        }
//                    }
//                }
//            }
//        }


        Log.w(TAG, "launchMatch: ${banks?.count()}, ${funds?.count()}")

        mAdapter.setNewInstance(matches)
    }

    companion object{
        private const val TAG = "MatchFragment"
    }
}