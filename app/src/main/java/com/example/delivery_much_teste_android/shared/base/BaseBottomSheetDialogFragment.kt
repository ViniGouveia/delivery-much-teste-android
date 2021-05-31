package com.example.delivery_much_teste_android.shared.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
abstract class BaseBottomSheetDialogFragment<ActivityType, PresenterType> :
    BottomSheetDialogFragment(),
    HasAndroidInjector, BaseContract.ActionView
        where ActivityType : BaseContract.ActivityView, PresenterType : BaseContract.Presenter {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    abstract val presenter: PresenterType
    abstract val layoutRes: Int

    protected var clickSubject = PublishSubject.create<() -> Unit>()
    private var disposable = CompositeDisposable()

    protected lateinit var parentActivity: ActivityType

    abstract fun initialize()

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentActivity = activity as ActivityType
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(layoutRes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickSubject
            .throttleFirst(CLICK_DELAY, TimeUnit.SECONDS)
            .subscribe { it() }
            .addTo(disposable)

        initialize()
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun showLoading(textRes: Int) = parentActivity.showLoading()

    override fun showError(error: String) = parentActivity.showError(error)

    override fun hideLoading() = parentActivity.hideLoading()

    override fun showOneButtonDialog(
        title: String,
        body: String,
        buttonText: String,
        callback: DialogInterface.OnClickListener?
    ) = parentActivity.showOneButtonDialog(
        title,
        body,
        buttonText,
        callback
    )

    override fun onDestroy() {
        presenter.detach()
        disposable.dispose()
        super.onDestroy()
    }

    override fun onDestroyView() {
        presenter.interrupt()
        disposable.clear()
        super.onDestroyView()
    }

    private companion object {
        const val CLICK_DELAY = 1L
    }
}
