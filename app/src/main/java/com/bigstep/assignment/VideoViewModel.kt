package com.bigstep.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bigstep.assignment.networks.RetrofitFactory
import com.bigstep.assignment.room.AppDatabase
import kotlinx.coroutines.*

class VideoViewModel() : ViewModel() {

    val observerModel = MutableLiveData<MutableList<VideoResult>>()
    val observerModelDB = MutableLiveData<MutableList<VideoResult>>()
    val progress = MutableLiveData<Boolean>()
    lateinit var db: AppDatabase

    fun getSongs() {
        progress.postValue(true)
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getSongs()
            withContext(Dispatchers.Main) {
                progress.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let {
                        observerModel.postValue(it.videoResults)
                    }
                }
            }
        }
    }

    fun setDB(db: AppDatabase) {
        this.db = db
    }

    fun getLocalSongs(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = async { db.getSongsDao().getAllSongs(true) }

            val response = result.await()

            observerModelDB.postValue(response.toMutableList())

            println("result songs ${response.size}")
        }
    }

    fun insertSong(song: VideoResult) {
        viewModelScope.launch(Dispatchers.IO) {
            song.viewed = true

            if (song.collectionArtistViewUrl == null)
                song.collectionArtistViewUrl = ""
            val result = async { db.getSongsDao().insert(song) }

            val response = result.await()

            println("result songs insert $response")
        }
    }


}