package com.vanaraleng.gardening.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.vanaraleng.gardening.R
import com.vanaraleng.gardening.databinding.FragmentAddPlantBinding
import com.vanaraleng.gardening.viewmodels.AddPlantViewModel

// Add and Update Plant
class AddPlantFragment : BaseFragment() {

    lateinit var binding: FragmentAddPlantBinding
    lateinit var viewModel: AddPlantViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddPlantViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editingPlantId = arguments?.getInt("editPlantId")
        if (editingPlantId != null) {
            // Update plant
            viewModel.getEditingPlant(editingPlantId)

            viewModel.editingPlant?.observe(viewLifecycleOwner) { editPlant ->
                if (editPlant != null) {
                    binding.nameEditText.setText(editPlant.name)
                    binding.typeEditText.setText(editPlant.type)
                    binding.plantDateEditText.setText(editPlant.plantingDate)
                    binding.wateringFequencyEditText.setText(editPlant.wateringFrequency.toString())
                    binding.addButton.setText(getString(R.string.update))
                }
            }
        }

        viewModel.addSuccess.observe(viewLifecycleOwner) { addSuccess ->
            if (addSuccess) {
                findNavController().popBackStack()
            }
        }

        binding.addButton.setOnClickListener {
            val errorMessage = getFieldErrorMessage()
            if (errorMessage != null) {
                Snackbar.make(binding.addButton, errorMessage, Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val name = binding.nameEditText.text.toString()
            val type = binding.typeEditText.text.toString()
            val watering = binding.wateringFequencyEditText.text.toString().toInt()
            val date = binding.plantDateEditText.text.toString()

            viewModel.addPlantOrUpdatePlan(name, type, watering, date)
        }
    }

    fun getFieldErrorMessage(): String? {
        if (binding.nameEditText.text.isEmpty()) {
            return "Please enter correct name."
        }

        if (binding.typeEditText.text.isEmpty()) {
            return "Please enter a type"
        }

        val watering = binding.wateringFequencyEditText.text.toString()
        if (watering.isEmpty() || watering.toIntOrNull() == null) {
            return "Please enter correct watering frequency."
        }

        if (binding.plantDateEditText.text.isEmpty()) {
            return "Please enter correct plant date"
        }

        return null
    }
}