package com.mars.account.infra.entities


import com.mars.account.core.models.Account
import org.hibernate.annotations.Type
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import javax.persistence.*


@Entity
class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    var id: Long? = null

    @Column(unique = true)
    @NotNull
    var userId: String = ""

    @Column
    @NotNull
    var passwordHashed: String = ""

    @Column(columnDefinition = "TINYINT(1)")
    @NotNull
    @Type(type = "org.hibernate.type.NumericBooleanType")
    var locked: Boolean = false

    @CreatedDate
    @Column(updatable = false)
    var createdAt: Instant? = null

    @LastModifiedDate
    @Column(updatable = true)
    var updatedAt: Instant? = null
}

fun Account.toEntity(): AccountEntity {
    val entity = AccountEntity()
    entity.id = this.id
    entity.userId = this.userId
    entity.passwordHashed = this.passwordHashed
    // detail

    // status
    entity.locked = this.locked
    return entity
}

fun AccountEntity.toModel(): Account =
        Account(
                id = this.id,
                userId = this.userId,
                passwordHashed = this.passwordHashed,
                locked = this.locked,
                createdAt = this.createdAt?.let { OffsetDateTime.ofInstant(it, ZoneId.systemDefault()) },
                updatedAt = this.updatedAt?.let { OffsetDateTime.ofInstant(it, ZoneId.systemDefault()) }
        )