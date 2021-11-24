package com.lvtu.koala.ui.record

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Bank

class BankAdapter : BaseQuickAdapter<Bank, BaseViewHolder>(R.layout.item_bank) {

    override fun convert(holder: BaseViewHolder, item: Bank) {
        holder.setText(R.id.bank_name, item.bankName)
        holder.setText(R.id.mount, "金额：${item.mount}万")
        holder.setText(R.id.in_price, "进价：${item.inPrice}元")
        holder.setText(R.id.register_count, "户数：${item.registerCount}")
        holder.setText(R.id.min_mount, "最小金额：${item.minMount}万")
        holder.setText(R.id.bank_person, "口子方：${item.bankPerson}")
        holder.setText(R.id.receive_person, "接单人：${item.receivePerson}")
        holder.setText(R.id.front_money_receive, "已收定金：${item.frontMoneyReceive}万")
        holder.setText(R.id.in_date, "进款日期：${item.inDate}")
    }
}