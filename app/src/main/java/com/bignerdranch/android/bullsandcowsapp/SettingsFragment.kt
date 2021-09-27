package com.bignerdranch.android.bullsandcowsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.setFragmentResult
import com.bignerdranch.android.bullsandcowsapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)

        binding.confirm2?.setOnClickListener {
            val checkedId = binding.radioGroup.checkedRadioButtonId
            if (checkedId != -1) {
                var difficulty = 0
                when (checkedId) {
                    R.id.normal, R.id.radioNormal -> difficulty = 15
                    R.id.hard, R.id.radioHard -> difficulty = 10
                    R.id.insane, R.id.radioInsane -> difficulty = 5
                }
                setFragmentResult("DIFF_KEY", bundleOf("DIFF" to difficulty))
            }
        }

        return binding.root
    }
}