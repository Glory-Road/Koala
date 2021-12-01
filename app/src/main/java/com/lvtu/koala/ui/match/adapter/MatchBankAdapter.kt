package com.lvtu.koala.ui.match.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Bank

class MatchBankAdapter: BaseQuickAdapter<Bank, BaseViewHolder>(R.layout.item_match_bank) {

    override fun convert(holder: BaseViewHolder, item: Bank) {
        holder.setText(R.id.bank_name, item.bankName)
        holder.setText(R.id.bank_amount, "金额:${item.mount}万")
        holder.setText(R.id.register_count, "户数要求:${item.registerCount}")
        holder.itemView.setBackgroundResource(if (item.checked) R.color.select_color else R.color.white)
    }
}