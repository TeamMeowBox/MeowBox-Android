package woo.sopt22.meowbox.Util

import android.content.Context
import android.content.SharedPreferences

class SharedPreference {
    private  var pref: SharedPreferences?=null

    /**
     * 공유설정 정보를 취득한다.
     */
    private fun getPref(cont: Context) {
        if (pref == null) {
            pref = cont.getSharedPreferences(SHARED_PREFS_CONFIGURATION, Context.MODE_PRIVATE)
        }
    }

    fun load(context: Context) {
        getPref(context)
    }

    @JvmOverloads
    fun getPrefLongData(key: String, defValue: Long = 0): Long {
        return pref!!.getLong(key, defValue)
    }

    @JvmOverloads
    fun getPrefIntegerData(key: String, defValue: Int = 0): Int {
        return pref!!.getInt(key, defValue)
    }

    @JvmOverloads
    fun getPrefFloatData(key: String, defValue: Float = 0f): Float {
        return pref!!.getFloat(key, defValue)
    }


    @JvmOverloads
    fun getPrefStringData(key: String, defValue: String = ""): String? {
        return pref!!.getString(key, defValue)
    }

    @JvmOverloads
    fun getPrefBooleanData(key: String, defValue: Boolean = false): Boolean {
        return pref!!.getBoolean(key, defValue)
    }

    fun setPrefData(key: String, value: Boolean) {
        val editor = pref!!.edit()

        editor.putBoolean(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: Int) {
        val editor = pref!!.edit()

        editor.putInt(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: Float) {
        val editor = pref!!.edit()

        editor.putFloat(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: Long) {
        val editor = pref!!.edit()

        editor.putLong(key, value)
        editor.commit()
    }

    fun setPrefData(key: String, value: String) {
        val editor = pref!!.edit()

        editor.putString(key, value)
        editor.commit()
    }


    fun setPrefDatas(values: Map<String, Any>) {
        val editor = pref!!.edit()

        val keyLists = values.keys.iterator()

        while (keyLists.hasNext()) {
            val key = keyLists.next()
            val value = values[key]

            if (value is String) {
                editor.putString(key, value)
            } else if (value is Boolean) {
                editor.putBoolean(key, value)
            } else if (value is Int) {
                editor.putInt(key, value)
            } else if (value is Long) {
                editor.putLong(key, value)
            } else if (value is Float) {
                editor.putFloat(key, value)
            }
        }

        editor.commit()
    }

    fun removeData(vararg keys: String) {
        val editor = pref!!.edit()

        for (key in keys) {
            editor.remove(key)
        }

        editor.commit()

    }

    companion object {
        // 공유명칭
        private val SHARED_PREFS_CONFIGURATION = "GithubConfiguration"

        @Volatile private var sharedPreferencesManager: SharedPreference? = null

        val instance: SharedPreference?
            get() {
                if (sharedPreferencesManager == null) {
                    synchronized(SharedPreference::class.java) {
                        if (sharedPreferencesManager == null)
                            sharedPreferencesManager = SharedPreference()
                    }
                }
                return sharedPreferencesManager
            }
    }
}