package com.blumer.msu.criminalintent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.blumer.msu.criminalintent.databinding.ListItemCrimeBinding
import com.blumer.msu.criminalintent.databinding.ListItemCrimePoliceBinding
import android.text.format.DateFormat

class CrimeHolder(
    val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        val formattedDate = DateFormat.format("MMM dd, yyyy", crime.date)
        binding.crimeDate.text = formattedDate.toString() // added for 3b

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class CrimePoliceHolder(val binding: ListItemCrimePoliceBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        val formattedDate = DateFormat.format("MMM dd, yyyy", crime.date)
        binding.crimeDate.text = formattedDate.toString() // added 3b

        binding.contactPoliceButton.setOnClickListener {

        }

        binding.crimeSolved.visibility = if (crime.isSolved) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class CrimeListAdapter(private val crimes: List<Crime>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val VIEW_TYPE_NORMAL = 1
    private val VIEW_TYPE_POLICE = 2
    private var isNormalView = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_POLICE -> {
                val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                CrimePoliceHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]

        when (holder) {
            is CrimeHolder -> {
                holder.bind(crime)
            }
            is CrimePoliceHolder -> {
                holder.bind(crime)
            }
        }
    }

    override fun getItemCount() = crimes.size //added with help of chatGPT
    override fun getItemViewType(position: Int): Int {
        return if (isNormalView) {
            isNormalView = !isNormalView
            VIEW_TYPE_NORMAL
        } else {
            isNormalView = !isNormalView
            VIEW_TYPE_POLICE
        }
    }
}
