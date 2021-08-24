package com.ecirstea.creepyrabbit.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecirstea.creepyrabbit.data.model.multimedia.FireResponse
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.utils.Constants.AUDIO_COLLECTION
import com.ecirstea.creepyrabbit.utils.Constants.FAVS_COLLECTION
import com.ecirstea.creepyrabbit.utils.SharedApp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject

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
       val hashMap : HashMap<String, String>
                = HashMap()

        hashMap["audioList"] = audioId;
        val items = listOf(audioId)

        val data = hashMapOf(
            "audioList" to arrayListOf(audioId),
        )



        favsCollection.document(SharedApp.prefs.name).get().addOnSuccessListener { document->
            if(document.data == null){
               // favsCollection.document(SharedApp.prefs.name).set(data)
                favsCollection.document(SharedApp.prefs.name).set(hashMap)
            }
        favsCollection.document(SharedApp.prefs.name).update("audioList", FieldValue.arrayUnion(audioId))
      //favsCollection.document(SharedApp.prefs.name).update("audioList", FieldValue.arrayUnion(hashMap))
          .addOnSuccessListener {
              Log.d(TAG, "saveFavorites: saved??")
          }
          .addOnFailureListener{
              Log.d(TAG, "saveFavorites: not saved??")
          }


        }

 /*       favsCollection.document(SharedApp.prefs.name).get()
            .addOnSuccessListener {
            Log.d(TAG, "saveFavorites: doc exists")
            }
            .addOnFailureListener{
                Log.d(TAG, "saveFavorites: docNOT EXISTS")
            }*/



    }
    fun deleteFavorite(audioId: String) {
        favsCollection.document(SharedApp.prefs.name).update("audioList", FieldValue.arrayRemove(audioId))
    }

    fun getFavoritesByUsername(): LiveData<MutableList<MultimediaData>> {
        Log.d(TAG, "getAllFavoriteAudios: +")

        var favsIds: MutableSet<String>
        var entries = listOf<String>()
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()

        favsCollection.document(SharedApp.prefs.name)
            .get()
            .addOnSuccessListener { fav ->

              val dataC = fav.toObject(FireResponse::class.java)
                Log.d(TAG, "getFavoritesByUsername: $dataC")
               audioCollection
                    .get()
                    .addOnSuccessListener { docs ->
                           for (document in docs) {


                          val audio = dataC?.audioList?.contains(document["mediaId"])
                            if(audio == true){
                                Log.d(TAG, "document is: found ${document["mediaId"]}")
                                audioList.add(document.toObject(MultimediaData::class.java))
                            }
                        }
                        mutableData.value = audioList
                    }
                    .addOnFailureListener { exception ->
                        Log.d(TAG, "Error getting documents: ", exception)
                    }
            }
        return mutableData
    }
}