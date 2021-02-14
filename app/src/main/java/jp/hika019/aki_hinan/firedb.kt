package jp.hika019.aki_hinan

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_prefecture.view.*

var firedb = FirebaseFirestore.getInstance()

val settings = FirebaseFirestoreSettings.Builder()
    .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
    .build()

var path = ""

var prefecture: String? = null
var city: String? = null

class firedb_class(val view: View, val context: Context){

    fun show_now_path(){
        Log.d("hoge", "path$path")


        view.now_path_textview.text += prefecture
    }

    fun next_fun(){
        if(prefecture != null && city == null){
            get_city()
        }

    }


    fun get_prefecture() {
        val TAG = "get_prefecture"

        var area_list: ArrayList<Any> = arrayListOf()

        firedb.collection("都道府県")
                .orderBy("num")
                .addSnapshotListener { areas, error ->
                    if (error != null) {
                        Log.w(TAG, "get_area -> failure", error)
                        return@addSnapshotListener
                    }
                    for (area in areas!!.documentChanges) {
                        area_list.add(area.document.id)

                        if(area.type != DocumentChange.Type.ADDED){
                            get_prefecture()
                        }
                    }



                    val adapter = CustomAdapter(area_list,  "area", view, context)
                    val layoutManager = LinearLayoutManager(context)

                    view.recyclervierw_prefecture.layoutManager = layoutManager
                    view.recyclervierw_prefecture.adapter = adapter
                    view.recyclervierw_prefecture.setHasFixedSize(true)

                }
    }

    fun get_city() {
        val TAG = "get_prefecture"

        show_now_path()

        var area_list: ArrayList<Any> = arrayListOf()

        Log.d("hoge", "get_city -> prefecture: $prefecture")

        if(prefecture != null){
            firedb.collection("都道府県")
                    .document(prefecture!!)
                    .collection("市町村")
                    .orderBy("num")
                    .addSnapshotListener { areas, error ->
                        if (error != null) {
                            Log.w(TAG, "get_area -> failure", error)
                            return@addSnapshotListener
                        }
                        for (area in areas!!.documentChanges) {
                            area_list.add(area.document.id)

                            if(area.type != DocumentChange.Type.ADDED){
                                get_prefecture()
                            }
                        }



                        val adapter = CustomAdapter(area_list,  "city", view, context)
                        val layoutManager = LinearLayoutManager(context)

                        view.recyclervierw_prefecture.layoutManager = layoutManager
                        view.recyclervierw_prefecture.adapter = adapter
                        view.recyclervierw_prefecture.setHasFixedSize(true)

                    }
        }else{
            get_prefecture()
        }


    }


}