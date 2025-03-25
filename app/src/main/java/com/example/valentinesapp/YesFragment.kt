package com.example.valentinesapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valentinesapp.adapters.PlacesAdapter
import com.example.valentinesapp.databinding.FragmentYesBinding
import com.example.valentinesapp.models.Places

class YesFragment : Fragment(){
    private var _binding: FragmentYesBinding? = null
    private val binding get() = _binding!!
    private val mActivity by lazy { requireActivity() as MainActivity }
    private lateinit var placesAdapter: PlacesAdapter
    private var places = arrayListOf(
        Places(R.drawable.foto9, "Alquilar una cabaña (HOY ya reservamos despues de volver de visitarle a Wild"),
        Places(R.drawable.foto10, "Cena romantica en tu casa o en mi casa"),
        Places(R.drawable.foto16, "Cena en algun restaurante lindo"),
        Places(R.drawable.foto11, "Cena en el Cit"),
        Places(R.drawable.foto12, "Dreams Ice Bar - Foz"),
        Places(R.drawable.foto13, "Ida al campo o a la quinta de Luque"),
        Places(R.drawable.foto14, "Cena en la parte de atras del auto de mama mirando las estrellas"),
        Places(R.drawable.foto15, "Ir a la playita de Villaflo donde tenes tu foto con Pruno y acampar ahi o picnic ahi")
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentYesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        binding.btConfirmar.visibility = View.GONE
        configureClickListeners()

    }

    private fun setupRecyclerView() {
        placesAdapter = PlacesAdapter(mActivity, places, object : PlacesAdapter.OnConfirmListener {
            override fun onConfirm(place: Places) {
                mActivity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment, ConfirmationFragment(requireContext(), place))
                    .addToBackStack(null)
                    .commit()
            }
        }
        )


        binding.rvPlaces.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = placesAdapter
        }
    }

    private fun configureClickListeners(){
        binding.btConfirmar.setOnClickListener{
            AlertDialog.Builder(requireActivity())
                .setMessage("Confirmado!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()
        }
    }
    private fun goToNextFragment() {
        mActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .addToBackStack(null)
            .commit()
    }

}