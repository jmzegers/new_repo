package cl.uandes.pichangapp.ui.views.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import cl.uandes.pichangapp.databinding.LoginBinding
import cl.uandes.pichangapp.ui.views.ProfileUser
import cl.uandes.pichangapp.ui.views.isLoggedIn
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.viewmodel.LogIn.LogInViewModel
import cl.uandes.pichangapp.ui.views.n
import cl.uandes.pichangapp.ui.views.elementId
import androidx.navigation.Navigation
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val users = ProfileUser
    private var _binding: LoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginBinding.inflate(
            inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerButton = binding.fromLoginToRegister

        registerButton.setOnClickListener {
            goToSignUp()
        }
        binding.loginViewModel = viewModel

        //observeViewModel()

        val emailInput = binding.editTextTextEmailAddress2
        val passwordInput = binding.editTextNumberPassword
        val loginButton = binding.fromLoginToHomepage
        n = 0

        val register = view.findViewById<TextView>(R.id.from_login_to_register)
        register.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.register)
        }

        loginButton.setOnClickListener {
            var verifiedCredentials: Boolean? = null

            if (emailInput.text.toString().isNotEmpty() && passwordInput.text.toString().isNotEmpty()) {
                verifiedCredentials = LogInViewModel().verifyUser(emailInput.text.toString(), passwordInput.text.toString())

                if (verifiedCredentials == true) {
                    isLoggedIn = true
                    val bottomNavigationView = activity?.findViewById<View>(R.id.navigation_view)
                    bottomNavigationView?.visibility = View.VISIBLE
                    val element = users.find { it.email == emailInput.text.toString() }
                    if (element != null) {
                        elementId = element.nameTeam
                    }
                    Navigation.findNavController(it).navigate(R.id.homepage)

                } else {
                    Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_LONG).show()
                  }

            }
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*
    private fun observeViewModel() {
        viewModel.credentialsAreValid.observe(viewLifecycleOwner) { areValid ->
            areValid?.let {
                if (it) {
                    isLoggedIn = true
                    findNavController().navigate(R.id.navigation_view)
                    val bottomNavigationView =
                        activity?.findViewById<View>(R.id.navigation_view)
                    bottomNavigationView?.visibility = View.VISIBLE
                } else {
                    Toast.makeText(context, "Credenciales inválidas", Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.emailLiveData.observe(viewLifecycleOwner) { email ->
            email?.let {
                if (!viewModel.emailDoesntExist(it) && email.isNotEmpty()) {
                    //binding.emailErrorTextView.visibility = View.VISIBLE
                } else {
                    //binding.emailErrorTextView.visibility = View.GONE
                }
            }
        }
    }

     */

    private fun goToSignUp() {
        findNavController().navigate(R.id.action_login_to_homepage)
    }


//    private fun registerUser(team: String,email: String, password: String, category: String, id: Int) {
//        val user = DataProfile(team, email, password, category, id)
//        users.add(user)
//    }
}