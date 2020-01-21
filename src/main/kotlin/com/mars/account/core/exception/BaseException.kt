package com.mars.account.core.exception

import java.lang.RuntimeException

open class BaseException(
        val code: String,
        override val message: String
) : RuntimeException(message)

const val ERR_EXIST_USERID = "exist_user_id"
class ExistUserIdException(override val message: String = "") : BaseException(ERR_EXIST_USERID, message)

const val ERR_NOT_EXIST_USERID = "not_exist_user_id"
class UserIdNotExistException(override val message: String = "") : BaseException(ERR_NOT_EXIST_USERID, message)