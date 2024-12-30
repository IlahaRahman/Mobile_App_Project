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


class LoginFragment: Fragment() {

    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var nextButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailTextInputLayout = view.findViewById(R.id.emailTextInputLayout)
        passwordTextInputLayout = view.findViewById(R.id.passwordTextInputLayout)
        nextButton = view.findViewById(R.id.nextButton)
        val registerNowTextView = view.findViewById<TextView>(R.id.registerNowTextView)

        registerNowTextView.setOnClickListener {
            (activity as? AccountActivity)?.replaceFragment(RegisterFragment())
        }

        nextButton.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun validateAndLogin (){
        val email = emailTextInputLayout.editText?.text.toString().trim()
        val password = passwordTextInputLayout.editText?.text.toString().trim()

        if (!CredentialsManager.isEmailValid(email)) {
            Toast.makeText(requireContext(), "Invalid email format", Toast.LENGTH_SHORT).show()
        } else if (!CredentialsManager.isPasswordValid(password)) {
            Toast.makeText(requireContext(), "Password cannot be empty", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent) }
    }

}