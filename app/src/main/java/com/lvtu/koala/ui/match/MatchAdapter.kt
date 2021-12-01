package com.lvtu.koala.ui.match

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.MatchOrder

class MatchAdapter: BaseQuickAdapter<MatchOrder, BaseViewHolder>(R.layout.item_match) {
    override fun convert(holder: BaseViewHolder, item: MatchOrder) {
        holder.setText(R.id.bank_name, item.funds)
        var funds = ""
        item.funds.forEach {
            "$funds,$it"
        }
        holder.setText(R.id.fund_name, funds)
    }
}