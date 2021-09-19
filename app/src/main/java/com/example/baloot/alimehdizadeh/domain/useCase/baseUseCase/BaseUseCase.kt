package com.example.baloot.alimehdizadeh.domain.useCase.baseUseCase

import com.example.baloot.alimehdizadeh.utils.Response
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

abstract class BaseUseCase {
    private var job: Job? = null
    private var coroutineScope: CoroutineScope? = CoroutineScope(Job() + Dispatchers.IO)
    private fun errorHandler(onFailure: ((message: String?) -> Unit)) =
        CoroutineExceptionHandler { context, error ->
            onFailure.invoke(error.message)
        }

    fun <P> execute(
        doInBackGround: suspend (suspend () -> Unit) -> P,
        onResult: ((P) -> Unit),
        onException: ((message: String?) -> Unit)
    ) {
        job?.cancel()
        job = coroutineScope?.launch(errorHandler(onException)) {
            val result = doInBackGround {

            }
            launch(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }


    }

    fun executeListFlow(
        doInBackground: suspend (suspend (ArrayList<Flow<Response<*>>>) -> Unit) -> ArrayList<Flow<Response<*>>>,
        onResult: ((ArrayList<Flow<Response<*>>>) -> Unit),
        onException: ((message: String?) -> Unit)
    ) {
        job?.cancel()
        job = coroutineScope?.launch(errorHandler(onException)) {
            val result = doInBackground {
                it.forEach { p ->
                    flowOf(p)
                }
            }
            launch(Dispatchers.Main) {
                onResult.invoke(result)
            }
        }
    }


}