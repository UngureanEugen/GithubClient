package com.yhn.githubclient.util

import java.lang.RuntimeException

class ApiException(val code: Int, override val message: String): RuntimeException()