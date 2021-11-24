package com.lvtu.koala.ui.record

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Fund

class FundAdapter : BaseQuickAdapter<Fund, BaseViewHolder>(R.layout.item_fund) {

    override fun convert(holder: BaseViewHolder, item: Fund) {
        holder.setText(R.id.fund_name, item.fundName)
        holder.setText(R.id.mount, "金额：${item.mount}万")
        holder.setText(R.id.out_price, "进价：${item.outPrice}元")
        holder.setText(R.id.register_count, "户数：${item.registerCount}")
        holder.setText(R.id.bank_card, "银行卡：${item.bankCard}")
        holder.setText(R.id.phone, "手机：${item.phone}")
        holder.setText(R.id.wechat, "微信：${item.wechat}")
    }
}