package com.example.valentinesapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import com.example.valentinesapp.ConfirmationFragment
import com.example.valentinesapp.MainFragment
import com.example.valentinesapp.R
import com.example.valentinesapp.databinding.DialogWatchOrConfirmBinding
import com.example.valentinesapp.models.Places

class WatchOrConfirmDialog(private var context: Context, private var place: Places, private val listener: ConfirmationListener) : Dialog(context) {

    private var _binding: DialogWatchOrConfirmBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestFeature(Window.FEATURE_NO_TITLE)
        //window?.setWindowAnimations(R.style.AppTheme_BottomDialog)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        _binding = DialogWatchOrConfirmBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        //window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setupListeners()
    }

    private fun setupListeners() {
        // Close dialog when clicking "Cancel"
        binding.btnCancel.setOnClickListener { dismiss() }

        // Open a video in YouTube, TikTok, or Chrome
        binding.btnWatchVideo.setOnClickListener {
            openVideo()
        }

        // Confirm button (you can add functionality)
        binding.btnConfirm.setOnClickListener {
            listener.onConfirm(place)
            dismiss()
            /*AlertDialog.Builder(context)
                .setMessage("Confirmado!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(false)
                .show()*/
            //dismiss() // Simply dismissing for now
        }
    }

    interface ConfirmationListener {
        fun onConfirm(place: Places)
    }

    private fun openVideo() {
        val context = this.context
        var videoUrl = when(place.descriptionOfPlace){
            "Alquilar una cabaña (HOY ya reservamos despues de volver de visitarle a Wild" -> "https://www.youtube.com/watch?v=LXVj5NNmDwM"
            "Cena romantica en tu casa o en mi casa" -> "https://www.youtube.com/shorts/l_CNkXenyR8"
            "Cena en el Cit" -> "https://www.youtube.com/watch?v=JbidFJaeys8"
            "Dreams Ice Bar - Foz" -> "https://www.youtube.com/watch?v=wKzjFq4yToI"
            "Ida al campo o a la quinta de Luque" -> "https://www.youtube.com/shorts/nUZdnPDuyUQ"
            "Cena en la parte de atras del auto de mama mirando las estrellas" -> "https://www.youtube.com/shorts/bgtBHaYcxDw"
            "Ir a la playita de Villaflo donde tenes tu foto con Pruno y acampar ahi o picnic ahi" -> "https://www.youtube.com/shorts/nUZdnPDuyUQ"
            "Cena en algun restaurante lindo" -> "https://www.youtube.com/shorts/iL2a2VL1KpE"
            else -> "https://www.youtube.com/watch?v=cuU2nMMeIdQ"
        }
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
