package com.example.valentinesapp

import android.os.Bundle
import androidx.fragment.app.Fragment  // ✅ Use this one instead.
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.valentinesapp.databinding.FragmentMainBinding
import kotlin.random.Random

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mActivity by lazy { requireActivity() as MainActivity }
    private var randomNumber = 5
    private var counter = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btNo.setOnClickListener {
            moveButtonRandomly()
            randomNumber = (1..7).random()
            setImageBackground(randomNumber)
            changeTextView()
        }

        binding.btYes.setOnClickListener {
            goToNextFragment()
        }

    }

    private fun goToNextFragment() {
        mActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, YesFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun changeTextView() {
        counter++
        when (counter) {
            1 -> binding.tvPreguntaValentine.text = "Are you sure?"
            2 -> binding.tvPreguntaValentine.text = "Really?"
            3 -> binding.tvPreguntaValentine.text = "Babyy :("
            4 -> binding.tvPreguntaValentine.text = "Stop pressing the No button ffs"
            5 -> binding.tvPreguntaValentine.text = "Just kidding darling, I love u"
            6 -> binding.tvPreguntaValentine.text = "Darling..."
            else -> {
                binding.tvPreguntaValentine.text = "Ok you must say yes, u dont have an option"
                binding.btNo.visibility = View.GONE
            }
        }
    }

    private fun moveButtonRandomly() {
        // Get the parent layout dimensions
        val parentWidth = binding.root.width
        val parentHeight = binding.root.height

        // Get current button dimensions
        val buttonWidth = binding.btNo.width
        val buttonHeight = binding.btNo.height

        // Generate random coordinates
        // Ensure the button stays within the parent layout
        val randomX = Random.nextInt(0, parentWidth - buttonWidth)
        val randomY = Random.nextInt(0, parentHeight - buttonHeight)

        // Animate the button movement
        binding.btNo.animate()
            .x(randomX.toFloat())
            .y(randomY.toFloat())
            .setDuration(300)
            .start()
    }

    private fun setImageBackground(number: Int) {
        when (number) {
            1 -> binding.clBackground.setBackgroundResource(R.drawable.foto1)
            2 -> binding.clBackground.setBackgroundResource(R.drawable.foto2)
            3 -> binding.clBackground.setBackgroundResource(R.drawable.foto3)
            4 -> binding.clBackground.setBackgroundResource(R.drawable.foto4)
            6 -> binding.clBackground.setBackgroundResource(R.drawable.foto6)
            7 -> binding.clBackground.setBackgroundResource(R.drawable.foto7)
            else -> binding.clBackground.setBackgroundResource(R.drawable.foto5)
        }

    }

    /*private fun moveButtonRandomly(view: View) {
        val parent = view.parent as View // Get parent layout

        parent.post {
            val parentPaddingLeft = parent.paddingLeft
            val parentPaddingTop = parent.paddingTop
            val parentPaddingRight = parent.paddingRight
            val parentPaddingBottom = parent.paddingBottom

            val maxX = parent.width - parentPaddingLeft - parentPaddingRight - view.width
            val maxY = parent.height - parentPaddingTop - parentPaddingBottom - view.height

            if (maxX > 0 && maxY > 0) {
                val newX = Random.nextInt(0, maxX).toFloat()
                val newY = Random.nextInt(0, maxY).toFloat()

                view.animate()
                    .translationX(newX)
                    .translationY(newY)
                    .setDuration(300)
                    .start()
            }
        }
    }*/
}