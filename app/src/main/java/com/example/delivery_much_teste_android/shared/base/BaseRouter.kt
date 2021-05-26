package com.example.delivery_much_teste_android.shared.base

import androidx.fragment.app.FragmentActivity

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
abstract class BaseRouter<ActivityType : BaseContract.ActivityView>(
    protected val activity: FragmentActivity
) : BaseContract.Router {

    @Suppress("UNCHECKED_CAST")
    protected val parentActivity = activity as ActivityType
}
