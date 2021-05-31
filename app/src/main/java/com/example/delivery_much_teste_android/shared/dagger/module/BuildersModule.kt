package com.example.delivery_much_teste_android.shared.dagger.module

import com.example.delivery_much_teste_android.ui.MainActivity
import com.example.delivery_much_teste_android.ui.MainModule
import com.example.delivery_much_teste_android.ui.repositories.RepositoriesFragment
import com.example.delivery_much_teste_android.ui.repositories.RepositoriesModule
import com.example.delivery_much_teste_android.ui.repositories.details.RepositoryDetailsFragment
import com.example.delivery_much_teste_android.ui.repositories.details.RepositoryDetailsModule
import com.example.delivery_much_teste_android.ui.repositoryowner.RepositoryOwnerFragment
import com.example.delivery_much_teste_android.ui.repositoryowner.RepositoryOwnerModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Module
interface BuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [RepositoriesModule::class])
    fun bindRepositoriesFragment(): RepositoriesFragment

    @ContributesAndroidInjector(modules = [RepositoryDetailsModule::class])
    fun bindRepositoryDetailsFragment(): RepositoryDetailsFragment

    @ContributesAndroidInjector(modules = [RepositoryOwnerModule::class])
    fun bindRepositoryOwnerFragment(): RepositoryOwnerFragment
}