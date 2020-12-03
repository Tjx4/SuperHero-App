package co.za.immedia.superheroapp.customViews

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class SearchEditText(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs) {
    var oldText: String = ""

    private var viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var  onTextUpdatedCallBackFunction: (text: String) -> Unit? = {}

    init {
        var timer = Timer()
        var isFirstTime = true

        this?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val newText = s.toString()
                if(newText.isEmpty() || newText == oldText) return

                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        uiScope.launch {
                            onTextUpdatedCallBackFunction?.invoke(newText)
                        }
                    }

                }, 2000)

                oldText = newText
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                timer?.cancel()
            }
        })

    }


}