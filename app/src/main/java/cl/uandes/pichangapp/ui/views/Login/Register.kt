package cl.uandes.pichangapp.ui.views.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import cl.uandes.pichangapp.databinding.RegisterBinding
import cl.uandes.pichangapp.ui.views.*
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.viewmodel.LogIn.RegisterViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Register.newInstance] factory method to
 * create an instance of this fragment.
 */
class Register : Fragment() {
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

    //private var _binding: RegisterBinding? = null
    //private val binding get() = _binding!!
    //private val users = ProfileUser()

    private lateinit var binding: RegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = RegisterBinding.inflate(inflater, container, false)

        //binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        n = 0
        val emailInput = binding.editTextTextPersonName2
        val passwordInput = binding.textView18
        val nameTeamInput = binding.editTextTextPersonName3
        val categoryInput = binding.categoryProfile2

        binding.registerViewModel = viewModel

        val btn1 = view.findViewById<Button>(R.id.from_register_to_login);
        btn1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.login)
        }

        val btn2 = view.findViewById<Button>(R.id.from_register_to_homepage);
        btn2.setOnClickListener {
            if (viewModel.emailDoesntExist(emailInput.text.toString())) {
                isLoggedIn = true
                val bottomNavigationView = activity?.findViewById<View>(R.id.navigation_view)
                bottomNavigationView?.visibility = View.VISIBLE
                viewModel.registerUser(emailInput.text.toString(), passwordInput.text.toString(), nameTeamInput.text.toString(), categoryInput.text.toString(),
                    arrayOf(ProfileUser).size)
                elementId = nameTeamInput.text.toString()
                Navigation.findNavController(it).navigate(R.id.homepage)
            } else {
                Toast.makeText(context, "Este email ya se encuentra registrado.", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Register().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun registerUser(Team: String,Email: String, Password: String, Category: String, Id: Int) {
//        return ProfileUser().Profile.add.StructureProfile(nameTeam = Team, email = Email, password = Password, category = Category, id = Id)
//    }

}
