package com.ecirstea.creepyrabbit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.utils.Constants.AUDIO_COLLECTION
import com.ecirstea.creepyrabbit.utils.Constants.FAVS_COLLECTION
import com.ecirstea.creepyrabbit.utils.SharedApp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

private const val TAG = "FirestoreHelper"

class FirestoreHelper {
    private val firestore = FirebaseFirestore.getInstance()
    private val audioCollection = firestore.collection(AUDIO_COLLECTION)
    private val favsCollection = firestore.collection(FAVS_COLLECTION)

    private val audioList: MutableList<MultimediaData> = mutableListOf()
    
    fun getAllAudioFiles() : LiveData<MutableList<MultimediaData>> {
        Log.d(TAG, "getAllAudioFiles: all files here")
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()

        audioCollection.get().addOnSuccessListener { docs ->
            Log.d(TAG, "size: fromAudioDatabase: ${docs.size()}")
            for (document in docs) {
                Log.d(TAG, "document is: ${document.toObject(MultimediaData::class.java)}")
                audioList.add(document.toObject(MultimediaData::class.java))
            }
            mutableData.value = audioList
        }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        Log.d(TAG, "getAllAudioFiles: audioList ${audioList.size}")
        return mutableData
    }

    fun getAudioFilesByCategory(category: String) : LiveData<MutableList<MultimediaData>> {
        Log.d(TAG, "get audio by categories")
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()

        audioCollection
            .whereEqualTo("category", category)
            .get().addOnSuccessListener { docs ->
            for (document in docs) {
                Log.d(TAG, "document is: ${document.toObject(MultimediaData::class.java)}")
                audioList.add(document.toObject(MultimediaData::class.java))
            }
            mutableData.value = audioList
        }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }
        return mutableData
    }

    fun saveFavorites(audioId: String){
    /*    val hashMap : HashMap<String, String>
                = HashMap()

        hashMap["audioList"] = audioId;*/
        val items = listOf(audioId)


        favsCollection.document(SharedApp.prefs.name).update("audioList", FieldValue.arrayUnion(audioId))
            .addOnSuccessListener {
                Log.d(TAG, "saveFavorites: saved??")
            }
            .addOnFailureListener{
                Log.d(TAG, "saveFavorites: not saved??")
            }

    }
}