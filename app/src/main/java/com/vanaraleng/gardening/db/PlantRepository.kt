package com.vanaraleng.gardening.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.vanaraleng.gardening.models.Plant

class PlantRepository(application: Application) {

    private val plantDao: PlantDao
    val allPlants: LiveData<List<Plant>>

    init {
        val database = PlantDatabase.getDatabase(application)
        plantDao = database.plantDao()
        allPlants = plantDao.getAllPlants()
    }

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun delete(plantId: Int) {
        plantDao.delete(plantId)
    }

    fun getPlantById(plantId: Int): LiveData<Plant> {
        return plantDao.getPlantById(plantId)
    }
}