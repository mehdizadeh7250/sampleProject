package com.example.baloot.alimehdizadeh.domain.repository

import com.example.baloot.alimehdizadeh.domain.model.remote.GetEveryThingFromQuery
import com.example.baloot.alimehdizadeh.utils.Response
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface GetDataQueryRepository {

    suspend fun getEveryThingQuery(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int
    ): Flow<Response<GetEveryThingFromQuery>>
}