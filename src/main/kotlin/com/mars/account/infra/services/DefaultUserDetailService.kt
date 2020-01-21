package com.mars.account.infra.services

import com.mars.account.core.exception.UserIdNotExistException
import com.mars.account.core.repositories.AccountRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class DefaultUserDetailService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUserId(username) ?: throw UserIdNotExistException("UserId does not exist.")
        return User.withUsername(account.userId).password(account.passwordHashed).authorities(account.authorities).build()
    }
}
