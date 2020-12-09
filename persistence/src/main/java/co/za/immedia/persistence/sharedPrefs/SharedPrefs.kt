package co.za.immedia.persistence.sharedPrefs

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson

class SharedPrefs(private val application: Application) {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)
    private val FIRSTTIME = "firstTime"

    var skipIntro: Boolean
        get() {
            val json = sharedPreferences.getString(FIRSTTIME, "")
            return Gson().fromJson(json, Boolean::class.java) ?: false
        }
        set(isFirstTime) {
            val editor = sharedPreferences.edit()
            val connectionsJSONString = Gson().toJson(isFirstTime)
            editor.putString(FIRSTTIME, connectionsJSONString)
            editor.commit()
        }

    companion object{
        fun getInstance(application: Application): SharedPrefs {
            synchronized(this){
                var instance: SharedPrefs? = null

                if(instance == null){
                    instance = SharedPrefs(application)
                }

                return  instance
            }
        }
    }
}