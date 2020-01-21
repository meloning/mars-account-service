package com.mars.account.boundaries.controller

import com.mars.account.boundaries.dto.AccountDto
import com.mars.account.boundaries.dto.SignUpDto
import com.mars.account.boundaries.dto.toDto
import com.mars.account.core.usecases.SignUpUsecase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
        private val signUpUsecase: SignUpUsecase
) {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("v1/accounts")
    @PreAuthorize("hasAnyAuthority('MARS_CLIENT')")
    fun signUp(@RequestBody @Validated signUpDto: SignUpDto): AccountDto {
        val newAccount = signUpUsecase.execute(signUpDto)
        return newAccount.toDto()
    }
}