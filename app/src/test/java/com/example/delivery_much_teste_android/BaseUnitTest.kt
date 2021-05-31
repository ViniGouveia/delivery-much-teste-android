package com.example.delivery_much_teste_android

import androidx.annotation.StringRes
import com.example.delivery_much_teste_android.shared.network.ThrowableHandler
import com.example.delivery_much_teste_android.shared.provider.AppDisposableProvider
import com.example.delivery_much_teste_android.shared.provider.DisposableProvider
import com.example.delivery_much_teste_android.shared.provider.SchedulerProvider
import io.reactivex.schedulers.TestScheduler
import org.junit.Before

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
abstract class BaseUnitTest {
    protected lateinit var disposableProvider: DisposableProvider
    protected lateinit var testScheduler: TestScheduler
    protected lateinit var schedulerProvider: SchedulerProvider
    protected lateinit var throwableHandler: ThrowableHandler

    @StringRes
    protected var errorRes = R.string.throwable_handler_generic_message

    @Before
    fun setupTest() {
        disposableProvider = AppDisposableProvider()
        testScheduler = TestScheduler()
        schedulerProvider = TestSchedulerProvider(testScheduler)
        throwableHandler = TestThrowableHandler()

        initialize()
    }

    protected abstract fun initialize()
}
