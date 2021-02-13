package jp.hika019.aki_hinan

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

var firedb = FirebaseFirestore.getInstance()

val settings = FirebaseFirestoreSettings.Builder()
    .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
    .build()

class firedb_class(){
    var pash = ""

    fun get_area(){
        val TAG = "get_area"

        var area_str = ""

        firedb.collection("地方")
            .addSnapshotListener { areas, error ->
                if(error != null){
                    Log.w(TAG, "get_area -> failure", error)
                    return@addSnapshotListener
                }

                for(area in areas!!.documentChanges){

                }



        }
    }

    fun get_ken_list(){

    }


    fun hoge(){
        firedb.firestoreSettings = settings

    }


}