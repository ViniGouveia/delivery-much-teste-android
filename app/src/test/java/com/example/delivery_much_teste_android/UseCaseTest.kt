package com.example.delivery_much_teste_android

import org.junit.Before

/**
 * @author Vinicius Gouveia on 30/05/2021
 */
abstract class UseCaseTest {

    @Before
    fun setupTest() {
        initialize()
    }

    protected abstract fun initialize()
}
