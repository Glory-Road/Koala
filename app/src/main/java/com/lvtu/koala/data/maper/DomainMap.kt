package com.lvtu.koala.data.maper

import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.domain.MatchOrder
import com.lvtu.koala.data.local.entity.BankEntity
import com.lvtu.koala.data.local.entity.FundEntity
import com.lvtu.koala.data.local.entity.MatchOrderEntity

fun Bank.toEntity() = BankEntity(id, bankName, bankCode, mount, bankPerson, receivePerson, bankAddress, inPrice, frontMoneyReceive, registerCount, minMount, inDate, status)
fun MatchOrder.toEntity() = MatchOrderEntity(id, bankId,funds)
fun Fund.toEntity() = FundEntity(id, fundName, mount, outPrice, registerCount, status, planMount, unPlanMount, bankCard, wechat, phone, bankId)