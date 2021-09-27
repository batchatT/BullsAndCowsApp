package com.bignerdranch.android.bullsandcowsapp.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.bullsandcowsapp.R
import com.bignerdranch.android.bullsandcowsapp.databinding.FragmentStartGameBinding
import com.bignerdranch.android.bullsandcowsapp.game.GameViewModel
import com.bignerdranch.android.bullsandcowsapp.rules.RulesActivity


class StartGameFragment : Fragment() {

    lateinit var startGameViewModel: StartGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentStartGameBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_start_game, container, false
        )

        startGameViewModel = ViewModelProvider(this).get(StartGameViewModel::class.java)

        setFragmentResultListener("DIFF_KEY"){_, bundle ->
            startGameViewModel.difficulty = bundle.getInt("DIFF")
        }

binding.playButton.setOnClickListener {
    val bundle = Bundle()
    bundle.putInt("DIFF", startGameViewModel.difficulty)
    this.findNavController().navigate(
        R.id.action_startGameFragment_to_gameFragment, bundle)
}
        binding.rules.setOnClickListener {
            val Intent = Intent(context, RulesActivity::class.java)
            startActivity(Intent)
        }
        binding.settings.setOnClickListener {
            this.findNavController().navigate(R.id.action_startGameFragment_to_settingsFragment)
        }


        return binding.root
    }

}