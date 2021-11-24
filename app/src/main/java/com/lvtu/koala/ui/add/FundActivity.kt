package com.lvtu.koala.ui.add

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lvtu.koala.R

class FundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fund)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, AddFundFragment.create(intent.getIntExtra(
            FUND_ID, 0))).commit()
    }

    companion object{
        const val FUND_ID = "fund_id"

        fun start(context: Context, fundId: Int) {
            context.startActivity(Intent(context, FundActivity::class.java).apply {
                putExtra(FUND_ID, fundId)
            })
        }
    }
}