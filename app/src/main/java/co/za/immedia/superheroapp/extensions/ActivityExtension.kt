package co.za.immedia.superheroapp.extensions

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.superheroapp.R
import co.za.immedia.superheroapp.constants.ACTIVITY_TRANSITION
import co.za.immedia.superheroapp.constants.PAYLOAD_KEY

val SLIDE_IN_ACTIVITY = getTransitionAnimation(R.anim.slide_right, R.anim.no_transition)
val SLIDE_OUT_ACTIVITY =  getTransitionAnimation(R.anim.no_transition, R.anim.slide_left)
val FADE_IN_ACTIVITY = getTransitionAnimation(R.anim.fade_in, R.anim.no_transition)
val FADE_OUT_ACTIVITY = getTransitionAnimation(R.anim.no_transition, R.anim.fade_out)
val TRAIL_TO = getTransitionAnimation(R.anim.trail_out, R.anim.trail_in)
val TRAIL_FROM = getTransitionAnimation(R.anim.trail_in2, R.anim.trail_out2)

fun AppCompatActivity.navigateToActivity(activity: Class<*>, payload: Bundle?, transitionAnimation: Transition) {
    goToActivity(activity, transitionAnimation, payload)
}

private fun getTransitionAnimation(inAnimation: Int, outAnimation: Int): Transition {
    val transitionProvider = Transition()
    transitionProvider.inAnimation = inAnimation
    transitionProvider.outAnimation = outAnimation
    return transitionProvider
}

private fun AppCompatActivity.goToActivity(activity: Class<*>, transitionAnimation: Transition, payload: Bundle?) {
    val intent = Intent(this, activity)

    val fullPayload = payload ?: Bundle()
    fullPayload.putIntArray(ACTIVITY_TRANSITION, intArrayOf(transitionAnimation.inAnimation, transitionAnimation.outAnimation))

    intent.putExtra(PAYLOAD_KEY, fullPayload)
    startActivity(intent)
}

data class Transition (var inAnimation: Int = 0, var outAnimation: Int = 0)