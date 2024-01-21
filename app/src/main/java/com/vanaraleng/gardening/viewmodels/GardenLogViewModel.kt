package com.vanaraleng.gardening.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vanaraleng.gardening.db.PlantRepository
import com.vanaraleng.gardening.models.Plant
import kotlinx.coroutines.launch

class GardenLogViewModel(app: Application): AndroidViewModel(app) {
    private val repository: PlantRepository

    lateinit var allPlants: LiveData<List<Plant>>

    init {
        repository = PlantRepository(app)

        viewModelScope.launch {
            allPlants = repository.allPlants
        }
    }

    fun addSampleIfEmpty() {
        if ((allPlants.value?.size ?: 0) > 0) {
            return
        }

        // Add sample plants
        val samplePlants = mutableListOf<Plant>()
        samplePlants.add(Plant(name = "Rose", type = "Flower", wateringFrequency = 2, plantingDate = "2023-01-01"))
        samplePlants.add(Plant(name = "Tomato", type = "Vegetable", wateringFrequency = 3, plantingDate = "2023-02-15"))
        samplePlants.add(Plant(name = "Basil", type = "Herb", wateringFrequency = 1, plantingDate = "2023-03-10"))
        samplePlants.forEach {
            insert(it)
        }

        allPlants = repository.allPlants
    }

    private fun insert(plant: Plant) = viewModelScope.launch { repository.insert(plant) }

    fun getPlantById(plantId: Int) = repository.getPlantById(plantId)

    fun deletePlantById(plantId: Int) = viewModelScope.launch { repository.delete(plantId) }

}