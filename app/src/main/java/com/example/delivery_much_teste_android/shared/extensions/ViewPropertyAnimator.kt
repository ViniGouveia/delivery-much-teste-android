package com.example.delivery_much_teste_android.shared.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
inline fun ViewPropertyAnimator.setListener(
    crossinline onAnimationRepeat: (p0: Animator?) -> Unit = { _ -> },
    crossinline onAnimationEnd: (p0: Animator?) -> Unit = { _ -> },
    crossinline onAnimationCancel: (p0: Animator?) -> Unit = { _ -> },
    crossinline onAnimationStart: (p0: Animator?) -> Unit = { _ -> }
): ViewPropertyAnimator {
    val animatorListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) =
            onAnimationRepeat.invoke(p0)

        override fun onAnimationEnd(p0: Animator?) =
            onAnimationEnd.invoke(p0)

        override fun onAnimationCancel(p0: Animator?) =
            onAnimationCancel.invoke(p0)

        override fun onAnimationStart(p0: Animator?) =
            onAnimationStart.invoke(p0)
    }
    setListener(animatorListener)
    return this
}
