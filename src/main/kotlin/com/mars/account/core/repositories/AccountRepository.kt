package com.mars.account.core.repositories

import com.mars.account.core.models.Account

interface AccountRepository {
    fun save(account: Account): Account
    fun existByUserId(userId: String): Boolean
    fun findByUserId(userId: String): Account?
}