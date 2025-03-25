package com.example.valentinesapp

import android.os.Bundle
import com.example.valentinesapp.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity  // ✅ Import AppCompatActivity


class MainActivity : AppCompatActivity () {


    private var _binding: ActivityMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, MainFragment())
            .addToBackStack(null)
            .commit()
    }
}