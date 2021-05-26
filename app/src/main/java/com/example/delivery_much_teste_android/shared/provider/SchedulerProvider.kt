package com.example.delivery_much_teste_android.shared.provider

import io.reactivex.Scheduler

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
interface SchedulerProvider {
    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler
}
