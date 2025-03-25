package com.example.valentinesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valentinesapp.ConfirmationFragment
import com.example.valentinesapp.R
import com.example.valentinesapp.databinding.ItemIdeasValentineBinding
import com.example.valentinesapp.dialogs.WatchOrConfirmDialog
import com.example.valentinesapp.models.Places

class PlacesAdapter(private var context: Context, private val placeList: ArrayList<Places>, private val listener: OnConfirmListener) :
    RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>() {

    // ViewHolder class
    class PlaceViewHolder(val binding: ItemIdeasValentineBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val binding = ItemIdeasValentineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlaceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val currentPlace = placeList[position]

        with(holder.binding) {
            ivPlace.setImageResource(currentPlace.imagePlace)
            tvPlace.text = currentPlace.descriptionOfPlace
            ivPlace.setOnClickListener{
                var dialog = WatchOrConfirmDialog(context, currentPlace, object : WatchOrConfirmDialog.ConfirmationListener {
                    override fun onConfirm(place: Places) {
                        listener.onConfirm(place)
                    }
                })
                dialog.show()
            }
        }
    }

    interface OnConfirmListener {
        fun onConfirm(place: Places)
    }
    override fun getItemCount(): Int = placeList.size
}