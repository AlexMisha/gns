package com.shepard.gns

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shepard.gns.ui.login.LoginActivity

/**
 * @author shepard
 * @since 29.12.2017
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, LoginActivity::class.java))
    }
}
