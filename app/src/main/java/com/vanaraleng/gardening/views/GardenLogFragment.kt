package com.vanaraleng.gardening.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanaraleng.gardening.databinding.FragmentGardenLogBinding
import com.vanaraleng.gardening.viewmodels.GardenLogViewModel

class GardenLogFragment : BaseFragment() {

    private lateinit var viewModel: GardenLogViewModel
    private lateinit var binding: FragmentGardenLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.addButton.setOnClickListener {
            val direction =
                GardenLogFragmentDirections.actionLogFragmentToAddPlantFragment(
                    "Add New Plant"
                )
            findNavController().navigate(direction)
        }

        viewModel.allPlants.observe(viewLifecycleOwner) {

            // Add samples plant
            viewModel.addSampleIfEmpty()

            binding.recyclerView.adapter = GardenLogAdapter(it, onItemClick = { plantId ->
                val direction =
                    GardenLogFragmentDirections.actionLogFragmentToPlantDetailFragment()
                direction.plantId = plantId
                findNavController().navigate(direction)
            })

        }
    }

}