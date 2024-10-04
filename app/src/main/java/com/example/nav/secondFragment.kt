package com.example.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [secondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class secondFragment : Fragment() {
    private lateinit var sharedPrefManager: SharedPrefManager
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view=inflater.inflate(R.layout.fragment_second, container, false)
        val textView: TextView = view.findViewById(R.id.signinText)
        val username: EditText =view.findViewById(R.id.username)
        val password:EditText=view.findViewById(R.id.password)
        val REpassword:EditText=view.findViewById(R.id.REpassword)
        val loginButton:Button=view.findViewById(R.id.loginButton)

        sharedPrefManager = SharedPrefManager(requireContext())
        loginButton.setOnClickListener {
            createAccount(username, password, REpassword)
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment)
        }
        textView.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment)}

        return view
    }
    private fun createAccount(username: EditText, password: EditText, REpassword: EditText) {
        val user = username.text.toString()
        val pass = password.text.toString()
        val repass = REpassword.text.toString()

        if (user.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập tên người dùng", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass.length < 6) {
            Toast.makeText(requireContext(), "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != repass) {
            Toast.makeText(requireContext(), "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show()
            return
        }
        sharedPrefManager.saveData(user, pass)
        Toast.makeText(requireContext(), "Tạo tài khoản thành công!", Toast.LENGTH_SHORT).show()

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment secondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            secondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}