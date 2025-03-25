package com.example.valentinesapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.valentinesapp.adapters.PlacesAdapter
import com.example.valentinesapp.databinding.FragmentConfirmationBinding
import com.example.valentinesapp.databinding.FragmentYesBinding
import com.example.valentinesapp.models.Places


class ConfirmationFragment(private var context: Context, private var place: Places) : Fragment(){
    private var _binding: FragmentConfirmationBinding? = null
    private val binding get() = _binding!!
    private val mActivity by lazy { requireActivity() as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureUI()
        configureClickListeners()
    }

    private fun configureUI(){
        binding.clFragmentConfirmation.setBackgroundResource(place.imagePlace)
        binding.tvConfirmation.text = "Got it, me empiezo a mover para ${place.descriptionOfPlace}"
    }

    private fun configureClickListeners(){
        binding.btVerVideo.setOnClickListener{
            openVideo()
        }

        binding.btVolverAlInicio.setOnClickListener{
            volverAlInicio()
        }
    }
    private fun volverAlInicio() {
        mActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun openVideo() {
        val context = this.context
        var videoUrl = "https://www.youtube.com/watch?v=LgdxcvnQzu8"
        // Example video
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(videoUrl)
            setPackage("com.google.android.youtube") // Try YouTube first
        }

        // Try to open with YouTube, TikTok, or Chrome
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Open in a web browser if no app is found
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl))
            context.startActivity(webIntent)
        }
    }

}