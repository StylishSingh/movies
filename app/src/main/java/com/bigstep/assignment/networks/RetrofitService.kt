package com.bigstep.assignment.networks

import com.bigstep.assignment.VideoModel
import retrofit2.Response
import retrofit2.http.*

interface RetrofitService {

    @GET(APIEndPoints.SEARCH)
    suspend fun getSongs(): Response<VideoModel>


}