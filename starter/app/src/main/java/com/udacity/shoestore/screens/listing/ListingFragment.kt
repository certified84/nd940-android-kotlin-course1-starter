package com.udacity.shoestore.screens.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentListingBinding
import com.udacity.shoestore.databinding.ShoeItemBinding

class ListingFragment : Fragment() {

    private lateinit var binding: FragmentListingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_listing, container, false)

        val linearLayout: LinearLayout = binding.linearLayout
        val shoeViewModel = ShoeViewModel()

        shoeViewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
            shoeList.forEach { shoe ->
                val shoeBinding = ShoeItemBinding.inflate(layoutInflater)
                shoeBinding.apply {
                    tvShoeName.text = shoe.name
                    tvShoeCompany.text = shoe.company
                    tvShoeSize.text = shoe.size.toString()
//
                    val view = shoeBinding.root
                    linearLayout.addView(view)
                    val id = (shoeList.indexOf(shoe)).toString()

                    view.setOnClickListener {
                        it.findNavController().navigate(
                            ListingFragmentDirections.actionListingFragmentToDetailFragment(
                                shoe.name,
                                shoe.company,
                                shoe.size.toString(),
                                shoe.description,
                                null,
                                id
                            )
                        )
                    }
                }
            }
        }


        binding.fab.setOnClickListener {
            it.findNavController().navigate(
                ListingFragmentDirections.actionListingFragmentToDetailFragment(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
                )
            )
        }

        return binding.root
    }

}