package com.bignerdranch.android.bullsandcowsapp

import android.R.attr.defaultValue
import android.R.attr.key
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.bullsandcowsapp.databinding.FragmentEndGameBinding


class EndGameFragment : Fragment() {
    private lateinit var binding: FragmentEndGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_end_game, container, false)

        val bundle = this.arguments
        if (bundle != null) {
            binding.score.text = bundle.getInt("MyKey").toString()
        }
        binding.menu.setOnClickListener {
            this.findNavController().navigate(R.id.action_endGameFragment_to_startGameFragment)
        }
        binding.playAgain.setOnClickListener {
            this.findNavController().navigate(R.id.action_endGameFragment_to_gameFragment)
        }
        return binding.root
    }

}