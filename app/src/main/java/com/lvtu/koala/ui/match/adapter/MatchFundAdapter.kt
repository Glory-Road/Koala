package com.lvtu.koala.ui.match.adapter

import android.util.Log
import android.widget.CheckBox
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lvtu.koala.R
import com.lvtu.koala.data.domain.Fund

class MatchFundAdapter: BaseQuickAdapter<Fund, BaseViewHolder>(R.layout.item_match_fund) {
    private val _selects = mutableListOf<Fund>()
    val selects: List<Fund>
        get() = _selects
    private var selectListener: SelectListener? = null

    override fun convert(holder: BaseViewHolder, item: Fund) {
        holder.setText(R.id.fund_name, item.fundName)
        holder.setText(R.id.fund_amount, "金额:${item.mount}万")
        val checkedView = holder.getView<CheckBox>(R.id.checked)
        checkedView.isChecked = item.checked || item.bankId > 0
//        checkedView.setOnCheckedChangeListener { buttonView, isChecked ->
//            item.checked = isChecked
//            val position = holder.adapterPosition
//            val fund = getItem(position)
//            if (selects.contains(fund)) {
//                selects.remove(fund)
//            } else {
//                selects.add(fund)
//            }
//            this.selectListener?.onSelectListener(selects)
//        }
        holder.itemView.setOnClickListener {
            val checked = item.checked.not()
            Log.w("MatchHomeActivity", "convert: $checked")
            item.checked = checked
            val position = holder.adapterPosition
            notifyItemChanged(position)
            val fund = getItem(position)
            if (checked) {
                if (_selects.contains(fund).not()) {
                    _selects.add(fund)
                }
            } else {
                _selects.remove(fund)
            }
            this.selectListener?.onSelectListener(_selects)
        }
    }

    fun setSelectListener(listener: SelectListener) {
        this.selectListener = listener
    }

    interface SelectListener{
        fun onSelectListener(selects: List<Fund>)
    }
}