package com.example.nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var sharedPrefManager: SharedPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_first, container, false)
        val signupText: TextView = view.findViewById(R.id.signupText)
        val  usernameInput: TextView = view.findViewById(R.id.username)
        val  passwordInput: TextView = view.findViewById(R.id.password)
        val loginButton:Button = view.findViewById(R.id.loginButton)
        sharedPrefManager = SharedPrefManager(requireContext())

        loginButton.setOnClickListener{
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val (savedUsername, savedPassword) = sharedPrefManager.getData()

            if (username == savedUsername && password == savedPassword) {
                Toast.makeText(requireContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MainActivity2::class.java)
                startActivity(intent)

                activity?.finish()
            } else {
                Toast.makeText(requireContext(), "Tên người dùng hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show()
            }
        }
        signupText.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_secondFragment)}
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}