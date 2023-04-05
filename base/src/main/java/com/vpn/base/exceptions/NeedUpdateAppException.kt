package com.example.base.exceptions

class NeedUpdateAppException(private val string: String): Exception() {
    override val message: String
        get() = string
}