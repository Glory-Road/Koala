package com.lvtu.koala.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.lvtu.koala.R
import com.lvtu.koala.ui.add.AddActivity
import com.lvtu.koala.databinding.ActivityMainBinding
import com.lvtu.koala.ui.home.HomeFragment
import com.lvtu.koala.ui.record.RecordFragment
import com.lvtu.koala.ui.match.MatchFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val mBinding
        get() = _binding!!
    private var _binding: ActivityMainBinding? = null
    private val mNavigationMenu = mutableListOf<AppCompatTextView>()
    private val mFragmentTags by lazy {
        mutableListOf("MatchFragment", "HomeFragment","RecordFragment")
    }
    private var mCurrentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initView()
        initListener()
    }

    private fun initListener() {
    }

    private fun initView() {
        Log.w(TAG, "initView: ${mBinding.navigation.childCount}")
        for (index in 0 until mBinding.navigation.childCount) {
            Log.w(TAG, "for initView: $index")
            mBinding.navigation.getChildAt(index).setOnClickListener {
                Log.w(TAG, "setOnClickListener: ")
                val textView = it as AppCompatTextView
                if (textView.text.toString() == "添加") {
                    AddActivity.start(this)
                } else {
                    clickNavigationItem(index)
                }
            }
        }
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, MatchFragment(), mFragmentTags[mCurrentIndex])
            .commit()
        mBinding.navigation.getChildAt(mCurrentIndex).isSelected = true
    }

    private fun clickNavigationItem(index: Int) {
        Log.w(TAG, "clickNavigationItem: $index")
        if (mCurrentIndex == index) return
        val fragment = supportFragmentManager.findFragmentByTag(mFragmentTags[index])
        if (fragment == null) {
            Log.w(TAG, "clickNavigationItem add: $index")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, getFragment(index), mFragmentTags[index])
                .commit()
        } else {
            Log.w(TAG, "clickNavigationItem replace: $index")
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
        }
        mCurrentIndex = index
        updateNavigation()
    }

    private fun updateNavigation() {
        for (index in 0 until mBinding.navigation.childCount) {
            mBinding.navigation.getChildAt(index).isSelected = index == mCurrentIndex
        }
    }

    private fun getFragment(index: Int) = when (index) {
        0 -> MatchFragment()
        1 -> HomeFragment()
        2 -> RecordFragment()
        else -> HomeFragment()
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}