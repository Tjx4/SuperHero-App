package co.za.immedia.commons.base.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import co.za.immedia.commons.R
import co.za.immedia.commons.constants.ACTIVITY_TRANSITION
import co.za.immedia.commons.constants.PAYLOAD_KEY
import co.za.immedia.commons.constants.SUPERHERO
import co.za.immedia.commons.constants.VIEW_SUPRERHERO_ACTIVITY
import co.za.immedia.commons.extensions.SLIDE_IN_ACTIVITY
import co.za.immedia.commons.extensions.navigateToActivity
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.tasks.OnSuccessListener

abstract class BaseActivity : AppCompatActivity() {
    var isNewActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTransitions(this)
        isNewActivity = true
    }

    protected fun hideKeyboard(view: View){
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun initTransitions(activity: Activity) {
        try {
            val activityTransition = activity.intent.getBundleExtra(PAYLOAD_KEY)?.getIntArray(ACTIVITY_TRANSITION)
            activity.overridePendingTransition(activityTransition!![0], activityTransition[1])
        }
        catch (e: Exception) {
            Log.e("AT", "$e")
        }
    }

    var mySessionId: Int? = null

    fun downloadDynamicModule(dynamicModule: String) {
        var activity = this

        val splitInstallManager: SplitInstallManager = SplitInstallManagerFactory.create(this)
        val request: SplitInstallRequest = SplitInstallRequest
                .newBuilder()
                .addModule(dynamicModule)
                .build()

        val listener: SplitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { splitInstallSessionState ->
            if (splitInstallSessionState.sessionId() === mySessionId) {
                when (splitInstallSessionState.status()) {
                    SplitInstallSessionStatus.INSTALLED -> {
                        Toast.makeText(activity, getString(R.string.dynamic_module_downloaded), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        splitInstallManager.registerListener(listener)
        splitInstallManager.startInstall(request)
                .addOnFailureListener {
                    print("Exception: $it")
                }
                .addOnSuccessListener { sessionId ->
                    mySessionId = sessionId
                }
    }

}