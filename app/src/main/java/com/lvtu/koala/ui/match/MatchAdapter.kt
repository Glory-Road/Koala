package com.lvtu.koala.ui.match

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Match

class MatchAdapter: BaseQuickAdapter<Match, BaseViewHolder>(R.layout.item_match) {
    override fun convert(holder: BaseViewHolder, item: Match) {
        holder.setText(R.id.bank_name, item.bankName)
        var funds = ""
        item.fundName.forEach {
            "$funds,$it"
        }
        holder.setText(R.id.fund_name, funds)
    }
}