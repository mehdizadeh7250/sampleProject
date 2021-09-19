package com.example.baloot.alimehdizadeh.utils

import com.example.baloot.alimehdizadeh.domain.model.ErrorEntity

sealed class Response<out T> {
    object LoadingState : Response<Nothing>()
    data class ErrorState(var exception: ErrorEntity) : Response<Nothing>()
    data class SuccessState<T>(var data: T) : Response<T>()
}