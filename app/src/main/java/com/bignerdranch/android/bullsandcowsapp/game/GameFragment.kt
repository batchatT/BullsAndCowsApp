package com.bignerdranch.android.bullsandcowsapp.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.bullsandcowsapp.R
import com.bignerdranch.android.bullsandcowsapp.databinding.FragmentGameBinding

class GameFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var buttons: Array<Button>
    private lateinit var views: Array<TextView>
    private val adapter = GameAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game, container, false
        )

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = gameViewModel
        views = arrayOf(binding.text1, binding.text2, binding.text3, binding.text4)
        buttons = arrayOf(
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9,
            binding.button0
        )
        for (i in 0..9) {
            buttons[i].setOnClickListener(this)
        }
        binding.cancelButton.setOnClickListener { delNum() }

        binding.resultList.adapter = adapter

        binding.lifecycleOwner = viewLifecycleOwner

        val bundle = this.arguments

        if (bundle != null) {
            gameViewModel.difficulty = bundle.getInt("DIFF")
        }

        return binding.root

    }

    override fun onClick(view: View?) {

        for (i in 0..9) {
            if (view == buttons[i]) {
                views[gameViewModel.textCounter].text = buttons[i].text
                view.isEnabled = false
            }
        }

        if (gameViewModel.textCounter == 3) {

            gameViewModel.textCounter = 0

            val list = mutableListOf<Int>()

            for (i in 0..3) {
                val gue = views[i].text.toString().toInt()
                list.add(gue)
                views[i].text = ""
            }
            adapter.addRes(bullsAndCows(list))
            binding.resultList.smoothScrollToPosition(adapter.itemCount-1)
            if (list == gameViewModel.guess || adapter.itemCount == gameViewModel.difficulty) {
                val bundle = Bundle()
                bundle.putInt("MyKey", gameViewModel.difficulty - adapter.itemCount)
                this.findNavController().navigate(
                    R.id.action_gameFragment_to_endGameFragment, bundle)

            }

            for (i in 0..9) {
                buttons[i].isEnabled = true
            }

        } else {
            gameViewModel.textCounter++
        }

    }

    private fun delNum() {

        if (gameViewModel.textCounter > 0) {
            gameViewModel.textCounter--
            for (i in 0..9) {
                if (views[gameViewModel.textCounter].text == buttons[i].text) {
                    buttons[i].isEnabled = true
                }
            }
            views[gameViewModel.textCounter].text = ""
        }
    }

    private fun bullsAndCows(list: MutableList<Int>): Res {
        var _bulls = 0
        var _cows = 0
        for (i in 0..3) {
            for (j in 0..3) {
                if (i == j && list[i] == gameViewModel.guess[j]) {
                    _bulls++
                } else if (list[i] == gameViewModel.guess[j]) {
                    _cows++
                }
            }
        }
        return Res(list.joinToString(prefix = "", postfix = "", separator = ""), _bulls, _cows)
    }

}
