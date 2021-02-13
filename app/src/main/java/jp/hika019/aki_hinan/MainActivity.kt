package jp.hika019.aki_hinan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import kotlinx.android.synthetic.main.activity_main.*

var prefecture = ""
var city = ""

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclervierw.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        get_prefecture()

        button.setOnClickListener{
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

                val adapter = CustomAdapter(area_list, this, "area")
                val layoutManager = LinearLayoutManager(this)

                recyclervierw.layoutManager = layoutManager
                recyclervierw.adapter = adapter
                recyclervierw.setHasFixedSize(true)
            }
    }

    fun get_city(){
        val TAG = "get_city"
        var city_list: ArrayList<Any> = arrayListOf()

        if(prefecture != null){
            firedb.collection("都道府県")
                .document(prefecture)
                .collection("市町村")
                .addSnapshotListener{ citys, error ->
                    if(error != null){
                        Log.w(TAG, "get_city -> failure", error)
                        return@addSnapshotListener
                    }
                    for (city in citys!!.documentChanges) {
                        city_list.add(city.document.id)

                        if(city.type != DocumentChange.Type.ADDED){
                            get_city()
                        }
                    }

                    val adapter = CustomAdapter(city_list, this, "area")
                    val layoutManager = LinearLayoutManager(this)

                    recyclervierw.layoutManager = layoutManager
                    recyclervierw.adapter = adapter
                    recyclervierw.setHasFixedSize(true)

                }
        }
    }
}