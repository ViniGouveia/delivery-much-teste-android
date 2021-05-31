package com.example.delivery_much_teste_android.shared.base

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("TooManyFunctions")
abstract class BaseFragment<ActivityType, PresenterType> : Fragment(), BaseContract.ActionView,
    HasAndroidInjector
        where ActivityType : BaseContract.ActivityView,
              PresenterType : BaseContract.Presenter {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    protected lateinit var parentActivity: ActivityType
    protected var clickSubject = PublishSubject.create<() -> Unit>()
    private var disposable = CompositeDisposable()

    abstract val presenter: PresenterType
    abstract var layoutRes: Int

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        parentActivity = activity as ActivityType
        bindProperties()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSubject
            .throttleFirst(CLICK_DELAY, TimeUnit.SECONDS)
            .subscribe { it() }
            .addTo(disposable)

        bindViews()
        initialize()
    }

    override fun onDestroyView() {
        presenter.interrupt()
        disposable.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.detach()
        disposable.dispose()
        super.onDestroy()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    @Deprecated("")
    open fun bindViews() {
        // not used
    }

    open fun bindProperties() = Unit

    abstract fun initialize()

    override fun showLoading(textRes: Int) = parentActivity.showLoading(textRes)

    override fun hideLoading() = parentActivity.hideLoading()

    override fun showError(error: String) = parentActivity.showError(error)

    override fun showOneButtonDialog(
        title: String,
        body: String,
        buttonText: String,
        callback: DialogInterface.OnClickListener?
    ) = parentActivity.showOneButtonDialog(title, body, buttonText, callback)

    private companion object {
        const val CLICK_DELAY = 1L
    }
}
