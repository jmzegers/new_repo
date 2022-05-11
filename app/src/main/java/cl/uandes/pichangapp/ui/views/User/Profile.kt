package cl.uandes.pichangapp.ui.views.User

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import cl.uandes.pichangapp.databinding.ProfileBinding
import cl.uandes.pichangapp.ui.views.ProfileUser
import cl.uandes.pichangapp.ui.views.elementId
import cl.uandes.pichangapp.ui.views.n
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.viewmodel.User.ProfileViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class Profile : Fragment() {
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

    private var _binding: ProfileBinding? = null
    private val binding get() = _binding!!
    private val users = ProfileUser
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        n = 0
        val text1 = view.findViewById<TextView>(R.id.NameOfTeam)
        val text2 = view.findViewById<TextView>(R.id.emailProfile)
        val text3 = view.findViewById<TextView>(R.id.passwordProfile)
        val text4 = view.findViewById<TextView>(R.id.categoryProfile)
        val image = view.findViewById<ImageView>(R.id.equipo)

        image.setImageResource(R.mipmap.team1)
        val user = users.find { it.nameTeam == elementId }
        if (user != null) {
            text1.text = user.nameTeam
            text2.text = user.email
            text3.text = user.password
            text4.text = user.category
        }

        val btn1 = view.findViewById<Button>(R.id.from_perfil_to_homepage);
        btn1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.homepage)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}