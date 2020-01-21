package com.mars.account.infra.repositories

import com.mars.account.infra.entities.AccountEntity
import org.springframework.data.repository.CrudRepository

interface AccountEntityRepository : CrudRepository<AccountEntity, Long> {
    fun findByUserId(userId: String): AccountEntity?
    fun existByUserId(userId: String): Boolean
}