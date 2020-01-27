package com.mars.account.config

import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@Profile("baseMock")
class BeanProvider {

    @Bean(name = ["PasswordEncoder"])
    @Primary
    fun passwordEncoder(): PasswordEncoder {
        return Mockito.mock(PasswordEncoder::class.java)
    }
}