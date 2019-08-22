package com.deepshooter.encryptedsharedpreferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

object SharedPreferenceUtils {

    private val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
    private val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)

    private const val myPrefsName = "MyPrefsFile"
    private const val userNameKey = "user_mame_key"

    fun setUsername(userName: String, context: Context) {
        val sharedPreferences = EncryptedSharedPreferences
                .create(myPrefsName,
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        val sharedPrefsEditor = sharedPreferences.edit()
        sharedPrefsEditor.putString(userNameKey, userName)
        sharedPrefsEditor.apply()
    }

    fun getUserName(context: Context): String {
        val sharedPreferences = EncryptedSharedPreferences
                .create(myPrefsName,
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)

        return sharedPreferences.getString(userNameKey, "Not Available")
    }

}