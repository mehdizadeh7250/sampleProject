package com.example.baloot.alimehdizadeh.data.source.network.routes

import com.example.baloot.alimehdizadeh.domain.model.remote.GetEveryThingFromQuery
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    fun getTopArtistListTracks(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int
    ): Flow<GetEveryThingFromQuery>

}