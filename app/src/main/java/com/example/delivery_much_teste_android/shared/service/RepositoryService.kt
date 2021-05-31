package com.example.delivery_much_teste_android.shared.service

import com.example.delivery_much_teste_android.shared.model.repository.RepositoryResponse
import com.example.delivery_much_teste_android.shared.model.repositoryowner.RepositoryOwnerInfoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
interface RepositoryService {

    @GET("/repositories")
    fun fetchRepositories(): Single<List<RepositoryResponse>>

    @GET("/users/{username}")
    fun fetchRepositoryOwnerInfo(
        @Path("username") ownerUsername: String
    ): Single<RepositoryOwnerInfoResponse>
}