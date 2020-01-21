package com.mars.account.boundaries.dto

import com.mars.account.core.models.Account

data class AccountDto(
        val userId: String,
        val locked: Boolean
)

fun Account.toDto() = AccountDto(
        userId = this.userId,
        locked = this.locked
)