package co.za.immedia.superheroapp.nav

import android.content.Intent

private const val PACKAGE_NAME = "co.za.immedia"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }

object SearchNavigation : Navigation {
    private const val INTRO = "intro.IntroActivity"
    override fun getIntent(): Intent? = INTRO.loadIntentOrNull()
}

interface Navigation {
    fun getIntent(): Intent?
}