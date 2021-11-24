package com.lvtu.koala.data.maper

import com.lvtu.koala.data.domain.Bank
import com.lvtu.koala.data.domain.Fund
import com.lvtu.koala.data.local.entity.BankEntity
import com.lvtu.koala.data.local.entity.FundEntity

fun BankEntity.toDomain() = Bank(id, bankName, bankCode, mount, bankPerson, receivePerson, bankAddress, inPrice, frontMoneyReceive, registerCount, minMount, inDate)
fun FundEntity.toDomain() = Fund(id, fundName, mount, outPrice, registerCount, status, planMount, unPlanMount, bankCard, wechat, phone)