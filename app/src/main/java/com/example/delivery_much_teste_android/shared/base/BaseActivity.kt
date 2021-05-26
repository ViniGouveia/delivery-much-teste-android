package com.example.delivery_much_teste_android.shared.base

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.GenericDialog
import com.example.delivery_much_teste_android.shared.extensions.setListener
import com.google.android.material.appbar.MaterialToolbar
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("TooManyFunctions")
abstract class BaseActivity<PresenterType> : AppCompatActivity(), BaseContract.ActivityView,
    HasAndroidInjector where PresenterType : BaseContract.Presenter {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    abstract var presenter: PresenterType
    abstract var layoutRes: Int

    open var frameLayoutId = NO_FRAME_LAYOUT_RES

    override var toolbar: MaterialToolbar? = null
        set(value) {
            setSupportActionBar(value)
            supportActionBar?.elevation = 0f
            field = value
        }

    override var toolbarTitle: String
        get() = supportActionBar?.title.toString()
        set(value) {
            supportActionBar?.title = value
        }

    private lateinit var loading: View
    protected var clickSubject = PublishSubject.create<() -> Unit>()
    private var disposable = CompositeDisposable()

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(layoutRes)

        clickSubject
            .throttleFirst(CLICK_DELAY, TimeUnit.SECONDS)
            .subscribe { it() }
            .addTo(disposable)

        loading = LayoutInflater.from(this)
            .inflate(R.layout.fullscreen_loading_layout, null, false)

        addContentView(
            loading,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        loading.visibility = View.GONE

        initialize()

        toolbarTitle = EMPTY_STRING
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    protected abstract fun initialize()

    override fun showOneButtonDialog(
        title: String,
        body: String,
        buttonText: String,
        callback: DialogInterface.OnClickListener?
    ) = GenericDialog(this, title, body)
        .oneButtonDialog(
            buttonText,
            callback
        )
        .show()

    override fun showLoading(textRes: Int) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )

        loading.apply {
            animate()
                .alpha(ANIMATION_ALPHA_VISIBLE)
                .setDuration(ANIMATION_DURATION)
                .setListener(
                    onAnimationStart = {
                        visibility = View.VISIBLE
                    }
                )
        }
    }

    override fun hideLoading() {
        loading.apply {
            animate()
                .alpha(ANIMATION_ALPHA_GONE)
                .setDuration(ANIMATION_DURATION)
                .setListener(
                    onAnimationEnd = {
                        visibility = View.GONE

                        Handler(Looper.myLooper()!!).postDelayed({
                            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        }, DELAY_TO_ENABLE_SCREEN_AFTER_LOADING)
                    }
                )
        }
    }

    override fun openFragment(fragment: BaseFragment<*, *>) {
        check(frameLayoutId != NO_FRAME_LAYOUT_RES) { "Container para fragmentos inexistente." }

        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(frameLayoutId, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    override fun setRootFragment(fragment: BaseFragment<*, *>) {
        check(frameLayoutId != NO_FRAME_LAYOUT_RES) { "Container para fragmentos inexistente." }

        val rootFragmentTag = "rootFragment"

        if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.popBackStack(
                rootFragmentTag,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }

        supportFragmentManager
            .beginTransaction()
            .replace(frameLayoutId, fragment)
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .addToBackStack(rootFragmentTag)
            .commit()
    }

    override fun onBackPressed() {
        val fragmentQuantity = supportFragmentManager.backStackEntryCount

        if (fragmentQuantity <= LAST_FRAGMENT_COUNT) {
            finish()
            return
        }

        supportFragmentManager.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        presenter.interrupt()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.detach()
        disposable.dispose()
        super.onDestroy()
    }

    private companion object {
        const val LAST_FRAGMENT_COUNT = 1
        const val NO_FRAME_LAYOUT_RES = 0
        const val ANIMATION_ALPHA_GONE = 0F
        const val ANIMATION_ALPHA_VISIBLE = 1F
        const val ANIMATION_DURATION = 200L
        const val DELAY_TO_ENABLE_SCREEN_AFTER_LOADING = 200L
        const val EMPTY_STRING = ""
        const val CLICK_DELAY = 1L
    }
}
