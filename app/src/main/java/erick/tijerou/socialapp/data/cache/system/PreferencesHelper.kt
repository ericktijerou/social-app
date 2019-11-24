package erick.tijerou.socialapp.data.cache.system

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper constructor(context: Context) {

    companion object {
        private const val PREF_SOCIAL_PACKAGE_NAME = "erick.tijerou.socialapp.preferences"
        private const val PREF_KEY_LAST_CACHE = "last_cache"
    }

    private val socialPref: SharedPreferences

    init {
        socialPref = context.getSharedPreferences(PREF_SOCIAL_PACKAGE_NAME, Context.MODE_PRIVATE)
    }

    var lastCacheTime: Long
        get() = socialPref.getLong(PREF_KEY_LAST_CACHE, 0)
        set(lastCache) = socialPref.edit().putLong(PREF_KEY_LAST_CACHE, lastCache).apply()

}
