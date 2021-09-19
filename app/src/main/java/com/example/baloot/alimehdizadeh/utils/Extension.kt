package com.example.baloot.alimehdizadeh.utils

import android.util.Log
import com.example.baloot.alimehdizadeh.domain.repository.ErrorHandler
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.toResult(errorHandler: ErrorHandler): Flow<Response<T>> = this.map<T, Response<T>> {
    Log.d("CustomResult", "Success")
    Response.SuccessState(it)
}.onStart {
    Log.d("CustomResult", "Start")
    emit(Response.LoadingState)
}.catch {
    Log.d("CustomResult", "Error : $it")
    emit(Response.ErrorState(errorHandler.getError(it)))
}