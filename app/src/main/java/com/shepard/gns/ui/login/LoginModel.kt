package com.shepard.gns.ui.login

import android.content.Context
import android.content.Intent
import com.github.salomonbrys.kodein.LazyKodein
import com.github.salomonbrys.kodein.android.appKodein
import com.github.salomonbrys.kodein.instance
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.shepard.gns.database.dao.UserRepository
import com.shepard.gns.database.entity.User
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author shepard
 * @since 24.01.2017
 */
// TODO: unit tests
class LoginModel(val context: Context) {
    private val kodein = LazyKodein(context.appKodein)
    private val userRepository: UserRepository by kodein.instance()

    fun doLogin(loginHandler: (loginIntent: Intent) -> Unit) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        val googleSignInClient = GoogleSignIn
                .getClient(context, gso)
        loginHandler(googleSignInClient.signInIntent)
    }

    @Throws(IllegalArgumentException::class, ApiException::class)
    fun handleLoginResult(result: Task<GoogleSignInAccount>?): User {
        val account = result?.result ?: throw IllegalArgumentException("account is null")
        val user = User(name = account.displayName.toString(), email = account.email.toString(),
                photoUrl = account.photoUrl.toString())
        Observable.just(user)
                .subscribeOn(Schedulers.io())
                .subscribe { userRepository.save(it) }
        return user
    }

    fun getUsers(): Flowable<MutableList<User>> {
        return userRepository.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}