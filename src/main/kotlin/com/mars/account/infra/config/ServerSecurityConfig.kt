package com.mars.account.infra.config

import com.mars.account.infra.services.DefaultUserDetailService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class ServerSecurityConfig(
        private val userDetailsService: DefaultUserDetailService,
        private val basicAuthConfig: BasicAuthConfig
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder() as DelegatingPasswordEncoder
        encoder.setDefaultPasswordEncoderForMatches(BCryptPasswordEncoder())
        return encoder
    }

    @Autowired
    fun globalAuthentication(auth: AuthenticationManagerBuilder) {
        val logger = LoggerFactory.getLogger(this.javaClass)

        val builder = InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>()
        // client setup
        builder.withUser(basicAuthConfig.clientUsername)
                .password("{noop}${basicAuthConfig.clientPassword}")
                .authorities("MARS_CLIENT")
        builder.configure(auth)
        auth.userDetailsService(userDetailsService)

        logger.info("globalAuthentication SetUp Success")
    }

    override fun configure(http: HttpSecurity) {
        http
            // TODO: CustomCorsFilter impl
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll()
                .anyRequest().authenticated()
            .and()
                .csrf().disable()
                .httpBasic()
            .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}