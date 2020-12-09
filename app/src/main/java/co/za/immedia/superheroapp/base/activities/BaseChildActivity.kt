package co.za.immedia.superheroapp.features.base.activities

import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import co.za.immedia.superheroapp.extensions.SLIDE_OUT_ACTIVITY

abstract class BaseChildActivity : BaseActivity() {
    protected var childActionBar: ActionBar? = null

    override fun onResume() {
        super.onResume()
        if (isNewActivity) {
        }
    }

    protected fun setChildActionbarActivityDependencies(actionBar: ActionBar?) {
        childActionBar = actionBar
        childActionBar?.setDisplayUseLogoEnabled(false)
        childActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        overridePendingTransition(SLIDE_OUT_ACTIVITY.inAnimation, SLIDE_OUT_ACTIVITY.outAnimation)
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(SLIDE_OUT_ACTIVITY.inAnimation, SLIDE_OUT_ACTIVITY.outAnimation)
    }

}
