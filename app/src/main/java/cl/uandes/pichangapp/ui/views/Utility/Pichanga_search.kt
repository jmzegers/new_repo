package cl.uandes.pichangapp.ui.views.Utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.uandes.pichangapp.databinding.PichangaSearchBinding
import android.text.Editable
import android.text.TextWatcher
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import cl.uandes.pichangapp.data.models.DataMatch
import cl.uandes.pichangapp.ui.views.*
import cl.uandes.pichangapp.R
import cl.uandes.pichangapp.ui.viewmodel.LogIn.LogInViewModel
import cl.uandes.pichangapp.ui.viewmodel.Utility.SearchPichangasViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class Pichanga_search : Fragment(), ItemAdapter.ActionListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModel: SearchPichangasViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var detailsItemAdapter: ItemAdapter  // new
    private val allFighters = Matchesss // FakeData Direction //new
    private var mLastClickTime = System.currentTimeMillis() //new
    private val CLICK_TIME_INTERVAL: Long = 300     //new
    private var _binding: PichangaSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PichangaSearchBinding.inflate(inflater, container, false)
        detailsItemAdapter = ItemAdapter(allFighters.toMutableList(), this) //new
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contextSituation = "search"
        n = 0
        binding.searchPichangasViewModel = viewModel

        //recyclerview
        val fighterListView = binding.fighterListView
        fighterListView.layoutManager = LinearLayoutManager(context)
        fighterListView.adapter = detailsItemAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pichanga_search().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {  }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var searchText = p0.toString().lowercase()
                filterFighters(searchText)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun filterFighters(searchText: String) {
        val filteredFighters = allFighters.filter {
            it.teamOne!!
                .lowercase()
                .contains(searchText)
        }

        detailsItemAdapter.updateFighters(filteredFighters)
    }

    override fun goToFighterDetails(fighter: DataMatch) {
        /* ir al Fragment Fighter Details */
        // usando safe args...

        /* -> El siguiente codigo es para que al apretar dos items, no se caiga la app */
        val now = System.currentTimeMillis()
        if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
            return;
        }

        mLastClickTime = now;
        /* -> hasta aqui <- */

        // aqui mandamos el fullName del fighter seleccionado
        val bundle = bundleOf("fighterName" to fighter.teamOne.toString())
        // llamamos la accion para navegar y el argumento entregado
        findNavController().navigate(R.id.action_pichanga_search_to_selected_item, bundle)
    }

}