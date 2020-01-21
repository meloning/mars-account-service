package com.mars.account.infra.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class BasicAuthConfig(
        @Value("\${basicAuth.client.username}")
        val clientUsername: String,

        @Value("\${basicAuth.client.password")
        val clientPassword: String
)