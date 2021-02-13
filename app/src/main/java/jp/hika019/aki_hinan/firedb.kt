package jp.hika019.aki_hinan

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

var firedb = FirebaseFirestore.getInstance()

val settings = FirebaseFirestoreSettings.Builder()
    .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
    .build()

class firedb_class(val context : Context){
    var pash = ""



    fun get_ken_list(){

    }


    fun hoge(){
        firedb.firestoreSettings = settings

    }


}