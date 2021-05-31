package com.example.delivery_much_teste_android

import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {

    override fun io(): Scheduler = testScheduler

    override fun ui(): Scheduler = testScheduler

    override fun computation(): Scheduler = testScheduler
}
