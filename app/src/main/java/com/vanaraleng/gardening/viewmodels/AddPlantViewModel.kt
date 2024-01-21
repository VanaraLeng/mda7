package com.vanaraleng.gardening.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vanaraleng.gardening.db.PlantRepository
import com.vanaraleng.gardening.models.Plant
import kotlinx.coroutines.launch

class AddPlantViewModel(app: Application): AndroidViewModel(app) {
    private val repository: PlantRepository

    var editingPlant: LiveData<Plant>? = null
    val addSuccess = MutableLiveData<Boolean>()

    init {
        repository = PlantRepository(app)
    }

    fun addPlantOrUpdatePlan(
        name: String,
        type: String,
        watering: Int,
        plantDate: String) {

        val editPlant = editingPlant?.value

        if (editPlant != null) {
            // Update existing plant
            editPlant.let {
                editPlant.name = name
                editPlant.type = type
                editPlant.wateringFrequency = watering
                editPlant.plantingDate = plantDate

                updatePlant(editPlant)
            }

        } else {
            // Insert new plant
            val plant = Plant(name = name, type = type, wateringFrequency = watering, plantingDate = plantDate)
            addPlant(plant)
        }
        // Notify update change
        addSuccess.value = true
    }

    fun getEditingPlant(plantId: Int) {
        viewModelScope.launch {
            editingPlant = repository.getPlantById(plantId)
        }
    }
    private fun addPlant(plant: Plant) = viewModelScope.launch { repository.insert(plant) }
    private fun updatePlant(plant: Plant) = viewModelScope.launch { repository.update(plant) }
}