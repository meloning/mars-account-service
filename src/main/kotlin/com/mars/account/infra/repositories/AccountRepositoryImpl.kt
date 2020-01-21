package com.mars.account.infra.repositories

import com.mars.account.core.models.Account
import com.mars.account.core.repositories.AccountRepository
import com.mars.account.infra.entities.toEntity
import com.mars.account.infra.entities.toModel
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
        private val accountEntityRepository: AccountEntityRepository
) : AccountRepository {
    override fun save(account: Account): Account {
        return accountEntityRepository.save(account.toEntity()).toModel()
    }

    override fun existByUserId(userId: String): Boolean {
        return accountEntityRepository.existByUserId(userId)
    }

    override fun findByUserId(userId: String): Account? {
        return accountEntityRepository.findByUserId(userId)?.toModel()
    }
}