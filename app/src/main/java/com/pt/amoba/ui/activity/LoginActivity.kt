package com.pt.amoba.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.pt.amoba.R
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

        binding.textViewRememberPass.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.not_available))
                .setMessage(getString(R.string.function_to_develop))
                .setPositiveButton(getString(R.string.ok)) { dialogInterface, d ->

                }.create().show()
        }

        binding.buttonLogin.setOnClickListener {
            if (validEmail()) {
                loginUser()
            } else {
                Toast.makeText(this, getString(R.string.invalid_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun verifyLoggedUser() {
        viewModel.liveDataSession()
    }

    private fun loginUser() {
        showViews()
        val email = binding.editTextEmail.text.toString()
        val pass = binding.editTextPassword.text.toString()
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
        Toast.makeText(this, getString(R.string.an_error_occurred), Toast.LENGTH_SHORT).show()
    }

    private fun validEmail(): Boolean {
        val email = binding.editTextEmail.text.toString()
        val pass = binding.editTextPassword.text.toString()
        return email.isNotEmpty() && pass.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun showLoading() {
        binding.apply {
            progressBarLogin.visibility = View.VISIBLE

            editTextEmail.visibility = View.INVISIBLE
            editTextPassword.visibility = View.INVISIBLE
            buttonLogin.visibility = View.INVISIBLE
            textViewRememberPass.visibility = View.INVISIBLE
        }
    }

    private fun showViews() {
        binding.apply {
            progressBarLogin.visibility = View.GONE

            editTextEmail.visibility = View.VISIBLE
            editTextPassword.visibility = View.VISIBLE
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