package jp.hika019.aki_hinan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var area_str = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclervierw.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        get_area()
    }


    fun get_area() {
        val TAG = "get_area"
        var area_list: Array<Any> = arrayOf()

        firedb.collection("地方")
            .addSnapshotListener { areas, error ->
                if (error != null) {
                    Log.w(TAG, "get_area -> failure", error)
                    return@addSnapshotListener
                }

                for (area in areas!!.documentChanges) {
                    area_list += area.document.id
                }

                val adapter = CustomAdapter(area_list, this, "area")
                val layoutManager = LinearLayoutManager(this)

                recyclervierw.layoutManager = layoutManager
                recyclervierw.adapter = adapter
                recyclervierw.setHasFixedSize(true)
            }
    }
}