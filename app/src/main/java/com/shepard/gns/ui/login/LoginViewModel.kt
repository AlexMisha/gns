package com.shepard.gns.ui.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.shepard.gns.database.entity.User

/**
 * @author shepard
 * @since 24.01.2017
 */
// TODO: unit tests
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var handleLogin: (loginIntent: Intent) -> Unit
    lateinit var onFailLogin: (e: Exception) -> Unit

    private var model = LoginModel(application)

    var user: User? = null
    val photoUrl = MutableLiveData<String>()

    fun doLogin() = model.doLogin(handleLogin)

    fun handleLoginResult(result: Task<GoogleSignInAccount>?) {
        try {
            user = model.handleLoginResult(result)
            photoUrl.value = user?.photoUrl
        } catch (e: Exception) {
            e.printStackTrace()
            onFailLogin(e)
        }
    }

    fun init() {
        model.getUsers().subscribe {
            if (!it.isEmpty()) {
                user = it[0]
                photoUrl.value = user?.photoUrl
            }
        }
    }

    fun isLoggedIn() = user != null
}