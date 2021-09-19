package com.example.baloot.alimehdizadeh.data.repository

import com.example.baloot.alimehdizadeh.data.source.local.AppDataBase
import com.example.baloot.alimehdizadeh.data.source.network.routes.ApiService
import com.example.baloot.alimehdizadeh.domain.model.remote.GetEveryThingFromQuery
import com.example.baloot.alimehdizadeh.domain.repository.ErrorHandler
import com.example.baloot.alimehdizadeh.domain.repository.GetDataQueryRepository
import com.example.baloot.alimehdizadeh.utils.Response
import com.example.baloot.alimehdizadeh.utils.toResult
import kotlinx.coroutines.flow.Flow

class GetDataQueryRepositoryImpl(
    private val appDataBase: AppDataBase,
    private val retrofitApiServices: ApiService,
    private val errorHandler: ErrorHandler
) : GetDataQueryRepository {
    override suspend fun getEveryThingQuery(
        query: String,
        sortBy: String,
        apiKey: String,
        page: Int
    ): Flow<Response<GetEveryThingFromQuery>> =
        retrofitApiServices.getTopArtistListTracks(query, sortBy, apiKey, page)
            .toResult(errorHandler)

}