package com.example.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [changePassWord.newInstance] factory method to
 * create an instance of this fragment.
 */
class changePassWord : Fragment(R.layout.fragment_change_pass_word) {
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
        val view = inflater.inflate(R.layout.fragment_change_pass_word, container, false)

        // Initialize SharedPrefManager
        sharedPrefManager = SharedPrefManager(requireContext())

        // Initialize UI components
        val passwordEditText: EditText = view.findViewById(R.id.password)
        val rePasswordEditText: EditText = view.findViewById(R.id.REpassword)
        val changeButton: Button = view.findViewById(R.id.loginButton)


        changeButton.setOnClickListener {
            val newPassword = passwordEditText.text.toString().trim()
            val rePassword = rePasswordEditText.text.toString().trim()

            if (newPassword.isEmpty() || rePassword.isEmpty()) {
                Toast.makeText(requireContext(), "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (newPassword != rePassword) {
                Toast.makeText(requireContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sharedPrefManager.changePassword(newPassword)


            Toast.makeText(requireContext(), "Mật khẩu đã được thay đổi thành công!", Toast.LENGTH_SHORT).show()
        }

        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment changePassWord.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            changePassWord().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}