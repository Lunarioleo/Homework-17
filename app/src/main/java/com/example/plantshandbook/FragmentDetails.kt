package com.example.plantshandbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.plantshandbook.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso


class FragmentDetails : Fragment() {
    private val dataModel: DataModel by activityViewModels()
private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.message.observe(activity as LifecycleOwner){
            binding.apply {
                Picasso.get()
                    .load(it.images.lg)
                    .into(fragmentImg)
                fullName.text = it.biography.fullName
                placeOfBirth.text = it.biography.placeOfBirth
                firstAppearance.text = it.biography.firstAppearance
            }
        }

    }








}