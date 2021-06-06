package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()

                if (email.isNotEmpty() && password.isNotEmpty()) {
                    etEmailLayout.error = null
                    etPasswordLayout.error = null
                    progressBar.visibility = View.VISIBLE
                    val handler = Handler(Looper.myLooper()!!)
                    handler.postDelayed({
                        progressBar.visibility = View.GONE
                        it.findNavController()
                            .navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                    }, 3000)
                } else {
                    etEmailLayout.error = "*Required"
                    etPasswordLayout.error = "*Required"
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
//        toolbar.visibility = View.GONE
    }
}