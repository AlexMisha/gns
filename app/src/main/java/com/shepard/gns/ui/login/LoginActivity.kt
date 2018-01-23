package com.shepard.gns.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.shepard.gns.R
import com.shepard.gns.databinding.ActivityLoginBinding
import org.jetbrains.anko.design.snackbar

/**
 * @author shepard
 * @since 24.01.2017
 */
// TODO: Configure Google console. Throws ApiException with code 10 now
// TODO: UI tests
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.apply {
            handleLogin = { startActivityForResult(it, LOGIN_CODE) }
            onFailLogin = {
                snackbar(binding.root, "Error while trying to login", "Try again") {
                    viewModel.doLogin()
                }
            }
            init()
        }
        binding.viewModel = viewModel
        binding.executePendingBindings()

        viewModel.photoUrl.observe(this, Observer {
            it?.let {
                val options = RequestOptions().dontTransform()
                Glide.with(this).load(it).apply(options).into(binding.photo)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_CODE) {
            binding.viewModel?.handleLoginResult(GoogleSignIn.getSignedInAccountFromIntent(data))
                    ?: throw IllegalStateException("binding.viewModel is null")
        }
    }

    companion object {
        const val LOGIN_CODE = 234
    }
}