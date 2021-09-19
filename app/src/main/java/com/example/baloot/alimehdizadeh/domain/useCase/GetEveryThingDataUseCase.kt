package com.example.baloot.alimehdizadeh.domain.useCase

import com.example.baloot.alimehdizadeh.domain.model.remote.GetEveryThingFromQuery
import com.example.baloot.alimehdizadeh.domain.repository.GetDataQueryRepository
import com.example.baloot.alimehdizadeh.domain.useCase.baseUseCase.BaseUseCase
import com.example.baloot.alimehdizadeh.utils.Response
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query
import javax.inject.Inject

class GetEveryThingDataUseCase @Inject constructor(
    private val getEveryThingQueryRepository: GetDataQueryRepository
) : BaseUseCase() {

    fun getEveryThingQuery(
        query: String,
       sortBy: String,
         apiKey: String,
        page: Int,
        onResult: ((Flow<Response<GetEveryThingFromQuery>>) -> Unit),
        onException: ((message: String) -> Unit)
    ) {
        execute(
            doInBackGround = {
                getEveryThingQueryRepository.getEveryThingQuery(query, sortBy, apiKey, page)
            },
            onResult = {
                onResult.invoke(it)
            },
            onException = {
                it?.let { message ->
                    onException.invoke(message)
                }
            }
        )
    }
}