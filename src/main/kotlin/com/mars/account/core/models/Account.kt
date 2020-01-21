package com.mars.account.core.models

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.OffsetDateTime

data class Account(
        val id: Long?,
        val userId: String,
        val passwordHashed: String,
        val locked: Boolean,

        // timestamps
        val createdAt: OffsetDateTime?,
        val updatedAt: OffsetDateTime?
) : UserDetails {
    constructor(userId: String, passwordHashed: String) : this(
            id = null,
            userId = userId,
            passwordHashed = passwordHashed,
            locked = false,
            createdAt = null,
            updatedAt = null
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return userId
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return passwordHashed
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked
    }
}