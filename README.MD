# Gardening Journal

## Purpose 

Gardening Journey app is a Android app used to record planting log in user's garden. It is developed using Kotlin programming language with latest Android JetPack frameworks, including, ViewModel, LiveData, Navigation, Room Database and Coroutine. 


## Functionalities

- View Garden Log:  `GardenLogFragment.kt`
- Add New Plant: `AddPlantFragement.kt`
- View Plant Detail: `PlantDetailFragement.kt`
- Edit Plant: `AddPlantFragement.kt`
- Delete Plant: `PlantDetailFragement.kt`

## How To Build

After cloning the project, simply just open it in latest Android Studio and run it on a device or emulator.

On the first run of Garden Log Fragment, I pre-populate 3 sample plant records. You can delete them anytime. 

## Implementations

### Navigation
I am using Android Navigation framework to manage navigation between fragment. There is only activity to host the navigation with navigation graph see `nav_graph.xml`.

### ViewModel
Every fragment contains a ViewModel instance. Some fragments may shared ViewModel class; however, they have different viewLifeCycler owner. 

### LiveData
Most of properties in ViewModel is LiveData type which mean it can notify the fragment to update the view state.

### Room Database and Coroutine
Plant record is stored in Room database. By implementing Plant Data access object `@Dao` interface and creating PlantRepository to separate the layer of access. Every DB transaction is done in background thread by using Coroutine context. 
