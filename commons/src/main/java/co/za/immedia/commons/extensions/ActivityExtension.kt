package co.za.immedia.commons.extensions

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Organization.TITLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import co.za.immedia.commons.R
import co.za.immedia.commons.base.activities.BaseActivity
import co.za.immedia.commons.constants.ACTIVITY_TRANSITION
import co.za.immedia.commons.constants.LAYOUT
import co.za.immedia.commons.constants.PAYLOAD_KEY
import co.za.immedia.superheroapp.features.base.fragments.BaseDialogFragment


val SLIDE_IN_ACTIVITY = getTransitionAnimation(R.anim.slide_right, R.anim.no_transition)
val SLIDE_OUT_ACTIVITY =  getTransitionAnimation(R.anim.no_transition, R.anim.slide_left)
val FADE_IN_ACTIVITY = getTransitionAnimation(R.anim.fade_in, R.anim.no_transition)
val FADE_OUT_ACTIVITY = getTransitionAnimation(R.anim.no_transition, R.anim.fade_out)
val TRAIL_TO = getTransitionAnimation(R.anim.trail_out, R.anim.trail_in)
val TRAIL_FROM = getTransitionAnimation(R.anim.trail_in2, R.anim.trail_out2)

fun AppCompatActivity.navigateToActivity(
    activity: Class<*>,
    payload: Bundle?,
    transitionAnimation: Transition
) {
    goToActivity(activity, transitionAnimation, payload)
}

fun AppCompatActivity.navigateToActivity(
    packageName: String,
    className: String,
    payload: Bundle?,
    transitionAnimation: Transition
) {
    goToActivity2(packageName, className, transitionAnimation, payload)
}

private fun getTransitionAnimation(inAnimation: Int, outAnimation: Int): Transition {
    val transitionProvider = Transition()
    transitionProvider.inAnimation = inAnimation
    transitionProvider.outAnimation = outAnimation
    return transitionProvider
}

private fun AppCompatActivity.goToActivity(
    activity: Class<*>,
    transitionAnimation: Transition,
    payload: Bundle?
) {
    val intent = Intent(this, activity)

    val fullPayload = payload ?: Bundle()
    fullPayload.putIntArray(
        ACTIVITY_TRANSITION, intArrayOf(
            transitionAnimation.inAnimation,
            transitionAnimation.outAnimation
        )
    )

    intent.putExtra(PAYLOAD_KEY, fullPayload)
    startActivity(intent)
}

private fun AppCompatActivity.goToActivity2(
    packageName: String,
    className: String,
    transitionAnimation: Transition,
    payload: Bundle?
) {

    val intent = Intent()
    intent.setClassName(packageName, className)

    val fullPayload = payload ?: Bundle()
    fullPayload.putIntArray(
        ACTIVITY_TRANSITION, intArrayOf(
            transitionAnimation.inAnimation,
            transitionAnimation.outAnimation
        )
    )
    intent.putExtra(PAYLOAD_KEY, fullPayload)

    startActivity(intent)

}

fun BaseActivity.showDialogFragment(
    title: String,
    layout: Int,
    newFragmentBaseBase: BaseDialogFragment
) {
    val ft = this.supportFragmentManager.beginTransaction()
    var newFragment = getFragmentDialog(title, layout, newFragmentBaseBase)
    newFragment.show(ft, "dialog")
}

private fun getFragmentDialog(title: String, layout: Int, newFragmentBaseBase: BaseDialogFragment) : BaseDialogFragment {
    val payload = newFragmentBaseBase.arguments
    payload?.putString(TITLE, title)
    payload?.putInt(LAYOUT, layout)

    newFragmentBaseBase.arguments = payload
    return newFragmentBaseBase
}

data class Transition(var inAnimation: Int = 0, var outAnimation: Int = 0)

