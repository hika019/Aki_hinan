package jp.hika019.aki_hinan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import kotlinx.android.synthetic.main.activity_main2.view.*


class activity_prefecture: Fragment() {
    val this_context = getContext()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main2, container, false)


        view.recyclervierw.addItemDecoration(DividerItemDecoration(this_context!!, DividerItemDecoration.VERTICAL))

        get_prefecture(view)

        view.button.setOnClickListener{
        }

        return view
    }

    fun get_prefecture(view: View) {
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
                        get_prefecture(view)
                    }
                }

                val adapter = CustomAdapter(area_list, this_context!!, "area")
                val layoutManager = LinearLayoutManager(this_context!!)

                view.recyclervierw.layoutManager = layoutManager
                view.recyclervierw.adapter = adapter
                view.recyclervierw.setHasFixedSize(true)

            }
    }

    fun get_city(view: View){
        val TAG = "get_city"
        Log.d("hoge", "call")
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
                            get_city(view)
                        }
                    }

                    val adapter = CustomAdapter(city_list, this_context!!, "area")
                    val layoutManager = LinearLayoutManager(this_context!!)

                    view.recyclervierw.layoutManager = layoutManager
                    view.recyclervierw.adapter = adapter
                    view.recyclervierw.setHasFixedSize(true)

                }
        }
    }
}