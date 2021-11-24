package com.lvtu.koala.ui.add

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lvtu.koala.R

class BankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
        supportFragmentManager.beginTransaction().replace(android.R.id.content, AddBankFragment.create(intent.getIntExtra(BANK_ID, 0))).commit()
    }

    companion object{
        const val BANK_ID = "bank_id"

        fun start(context: Context, bankId: Int) {
            context.startActivity(Intent(context, BankActivity::class.java).apply {
                putExtra(BANK_ID, bankId)
            })
        }
    }
}