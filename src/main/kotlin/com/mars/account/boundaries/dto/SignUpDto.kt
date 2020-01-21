package com.mars.account.boundaries.dto

import javax.validation.constraints.NotBlank

data class SignUpDto(
        @field:NotBlank
        // @field:CustomValid TODO: check custom regex(dot) impl
        val userId: String,
        @field:NotBlank
        val password: String
)