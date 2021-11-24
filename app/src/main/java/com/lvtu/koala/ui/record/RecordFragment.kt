package com.lvtu.koala.ui.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lvtu.koala.databinding.FragmentMatchBinding
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

import android.content.Context
import android.graphics.Color
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lvtu.koala.databinding.FragmentRecordBinding
import com.lvtu.koala.weiget.ScaleTransitionPagerTitleView

import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView


class RecordFragment : Fragment() {
    private val mTitleDataList = mutableListOf("行口", "资方")
    private val mBinding
        get() = _binding!!
    private var _binding: FragmentRecordBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        val commonNavigator = CommonNavigator(requireContext())
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return mTitleDataList.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView? {
                val simplePagerTitleView: SimplePagerTitleView =
                    ScaleTransitionPagerTitleView(context)
                simplePagerTitleView.text = mTitleDataList[index]
                simplePagerTitleView.textSize = 18f
                simplePagerTitleView.normalColor = Color.parseColor("#616161")
                simplePagerTitleView.selectedColor = Color.parseColor("#f57c00")
                simplePagerTitleView.setOnClickListener { mBinding.viewPager.currentItem = index }
                return simplePagerTitleView
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                val indicator = LinePagerIndicator(context)
                indicator.startInterpolator = AccelerateInterpolator()
                indicator.endInterpolator = DecelerateInterpolator(1.6f)
                indicator.lineHeight = UIUtil.dip2px(context, 2.0).toFloat()
                indicator.lineWidth = UIUtil.dip2px(context, 8.0).toFloat()
                indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
                indicator.xOffset = UIUtil.dip2px(context, 10.0).toFloat()
                indicator.setColors(Color.parseColor("#f57c00"))
                return indicator
            }

            override fun getTitleWeight(context: Context?, index: Int): Float {
                return when (index) {
                    0 -> {
                        2.0f
                    }
                    1 -> {
                        1.2f
                    }
                    else -> {
                        1.0f
                    }
                }
            }
        }
        mBinding.magicIndicator.navigator = commonNavigator

        val adapter = MyFragmentStateAdapter(this)
        mBinding.viewPager.adapter = adapter
        mBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                mBinding.magicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                mBinding.magicIndicator.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                mBinding.magicIndicator.onPageScrollStateChanged(state)
            }
        })
    }


    private fun initListener() {
    }

    inner class MyFragmentStateAdapter(fragment: Fragment) :
        FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return if (position == 0)BankListFragment() else FundListFragment()
        }

        override fun getItemCount(): Int {
            return mTitleDataList.size
        }
    }
}