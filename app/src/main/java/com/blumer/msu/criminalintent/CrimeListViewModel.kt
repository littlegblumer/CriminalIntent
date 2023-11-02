package com.blumer.msu.criminalintent

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class CrimeListViewModel : ViewModel() {

    val crimes = mutableListOf<Crime>()

    init {
        for (i in 0 until 100) {
            val isPoliceRelated = i % 2 == 0
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = isPoliceRelated, // Set isSolved as isPoliceRelated for demonstration
                requiresPolice = isPoliceRelated // Set requiresPolice to control the layout
            )
            crimes += crime
        }
    }
}
