package com.ecirstea.creepyrabbit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.utils.Constants.AUDIO_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore

private const val TAG = "FirestoreHelper"

class FirestoreHelper {
    private val firestore = FirebaseFirestore.getInstance()
    private val audioCollection = firestore.collection(AUDIO_COLLECTION)
    private val audioList: MutableList<MultimediaData> = mutableListOf()


/*    private fun getAllAudios(): List<MultimediaData>{
            audioCollection.get().addOnSuccessListener { docs ->
                Log.d(TAG, "size: fromAudioDatabase: ${docs.size()}")
                for (document in docs) {
                    Log.d(TAG, "document is: ${document.toObject(MultimediaData::class.java)}")
                    audioList.add(document.toObject(MultimediaData::class.java))
                }
            }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }
         return audioList
        }*/

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
}