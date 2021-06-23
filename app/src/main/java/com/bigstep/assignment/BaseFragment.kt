package com.bigstep.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bigstep.assignment.room.AppDatabase

abstract class BaseFragment(id: Int) : Fragment(id) {

    lateinit var  db :AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db=Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java, "songsdb"
        ).fallbackToDestructiveMigration().build()

    }

}