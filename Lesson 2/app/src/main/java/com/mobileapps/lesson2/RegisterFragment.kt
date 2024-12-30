package com.mobileapps.lesson2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment :Fragment() {
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var registerButton: MaterialButton
    private lateinit var loginTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailTextInputLayout = view.findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = view.findViewById(R.id.passwordTextInputLayout)
        registerButton = view.findViewById(R.id.nextButton)
        loginTextView = view.findViewById(R.id.loginTextView)

        loginTextView.setOnClickListener {
            (activity as? AccountActivity)?.replaceFragment(LoginFragment())
        }

        registerButton.setOnClickListener {
            registerAccount()
        }
    }

    private fun registerAccount() {
        val email = emailTextInputLayout.editText?.text.toString().trim()
        val password = passwordTextInputLayout.editText?.text.toString().trim()
        var isValid = true

        if (!CredentialsManager.isEmailValid(email)) {
            emailTextInputLayout.error = "Invalid email format"
            isValid = false
        } else {
            emailTextInputLayout.error = null
        }

        if (!CredentialsManager.isPasswordValid(password)) {
            passwordTextInputLayout.error = "Password cannot be empty"
            isValid = false
        } else {
            passwordTextInputLayout.error = null
        }

        if (isValid) {
            val isRegistered = CredentialsManager.register(email, password)
            if (isRegistered) {
                Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), AccountActivity::class.java)
                startActivity(intent)
                    requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Email is already taken", Toast.LENGTH_SHORT).show()
            }
        }
    }
}