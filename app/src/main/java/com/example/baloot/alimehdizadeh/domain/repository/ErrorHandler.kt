package com.example.baloot.alimehdizadeh.domain.repository

import com.example.baloot.alimehdizadeh.domain.model.ErrorEntity

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorEntity
}