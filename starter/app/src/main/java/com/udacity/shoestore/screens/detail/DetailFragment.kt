package com.udacity.shoestore.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoeViewModel
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import kotlin.properties.Delegates

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var shoeName: String
    private var shoeSize by Delegates.notNull<Double>()
    private lateinit var company: String
    private lateinit var description: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        val shoeViewModel = ShoeViewModel()

        binding.apply {
            val args = arguments?.let { DetailFragmentArgs.fromBundle(it) }

            val shoeId = args?.shoeId

            if (args != null) {
                if (shoeId != null) {
                    tvDetailTitle.text = getString(R.string.edit_shoe)
                    etShoeName.setText(args.shoeName.toString())
                    etCompany.setText(args.company.toString())
                    etDescription.setText(args.description.toString())
                    etShoeSize.setText(args.shoeSize.toString())

                    shoeName = etShoeName.text.toString()
                    company = etCompany.text.toString()
                    description = etDescription.text.toString()
                    shoeSize = etShoeSize.text.toString().toDouble()
                } else
                    tvDetailTitle.text = getString(R.string.add_shoe)
            }

            btnCancel.setOnClickListener {
                it.findNavController()
                    .navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
            }

            btnDone.setOnClickListener { it ->
                if (shoeId != null) {
                    shoeViewModel.shoeList.observe(viewLifecycleOwner) {
                        val shoe = it[shoeId.toInt()]
                        shoe.name = shoeName
                        shoe.size = shoeSize
                        shoe.company = company
                        shoe.description = description
                        shoe.images = null

                        it.removeAt(shoeId.toInt())
                        it.add(shoeId.toInt(), shoe)
                    }
                } else {
                    shoeName = etShoeName.text.toString()
                    company = etCompany.text.toString()
                    description = etDescription.text.toString()
                    shoeSize = etShoeSize.text.toString().toDouble()
                    
                    shoeViewModel.shoeList.observe(viewLifecycleOwner) { shoeList ->
                        val shoe = Shoe(shoeName, shoeSize, company, description, null)
                        shoeList.add(shoe)
                    }
                    it.findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListingFragment())
                }
            }
        }
        return binding.root
    }
}