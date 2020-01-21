package com.mars.account.core.usecases

import com.mars.account.boundaries.dto.SignUpDto
import com.mars.account.core.exception.ExistUserIdException
import com.mars.account.core.models.Account
import com.mars.account.core.repositories.AccountRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class SignUpUsecase(
        private val accountRepository: AccountRepository,
        private val passwordEncoder: PasswordEncoder
) {

    fun execute(signUpDto: SignUpDto): Account {
        if (accountRepository.existByUserId(signUpDto.userId)) {
            throw ExistUserIdException("UserId(${signUpDto.userId}) already exists.")
        }

        val newAccount = Account(
            userId = signUpDto.userId,
            passwordHashed = passwordEncoder.encode(signUpDto.password)
        )

        return accountRepository.save(newAccount)
    }
}