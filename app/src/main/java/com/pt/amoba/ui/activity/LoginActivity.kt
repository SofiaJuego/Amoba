package com.pt.amoba.ui.activity


import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pt.amoba.MainActivity
import com.pt.amoba.data.api.ResponseLogin
import com.pt.amoba.data.viewmodel.ViewModel
import com.pt.amoba.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), ResponseLogin {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verifyLoggedUser()

        viewModel.mainState.observe(::getLifecycle, ::autoLogin)

        binding.buttonLogin.setOnClickListener {
            if (validEmail()) {
                loginUser()
            } else {
                Toast.makeText(this, "Campos invalidos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyLoggedUser() {
        viewModel.liveDataSession()
    }

    private fun loginUser() {
        showViews()
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        viewModel.loginUser(email, pass, this)
    }

    override fun onLoading() {
        showLoading()
    }

    override fun onSucessfull() {
        goToMain()
    }

    override fun onError() {
        showViews()
        Toast.makeText(this, "Algo salio mal con firebase", Toast.LENGTH_SHORT).show()
    }

    private fun validEmail(): Boolean {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPassword.text.toString()
        return email.isNotEmpty() && pass.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }


    private fun showLoading() {
        binding.apply {
            progressBarLogin.visibility = View.VISIBLE

            etEmail.visibility = View.INVISIBLE
            etPassword.visibility = View.INVISIBLE
            buttonLogin.visibility = View.INVISIBLE
            textViewRememberPass.visibility = View.INVISIBLE
        }
    }

    private fun showViews() {
        binding.apply {
            progressBarLogin.visibility = View.GONE

            etEmail.visibility = View.VISIBLE
            etPassword.visibility = View.VISIBLE
            buttonLogin.visibility = View.VISIBLE
            textViewRememberPass.visibility = View.VISIBLE
        }
    }

    private fun autoLogin(boolean: Boolean) {
        if (boolean) {
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}