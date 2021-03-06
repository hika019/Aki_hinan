package jp.hika019.aki_hinan

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_recycler.view.*

class CustomAdapter(private val area_List: ArrayList<Any>,
                    private val serch_level: String,
                    private val view: View,
                    private val context: Context
): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {


    lateinit var listener: AdapterView.OnItemClickListener

    class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val area_text = view.item_recycler_textview.item_recycler_textview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.item_recycler, parent, false)

        return CustomViewHolder(item)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var area_title = ""

        if(serch_level == "area"){
            area_title = area_List[position] as String
            Log.d("hoge", "call3")


            holder.area_text.text = area_title
            holder.view.setOnClickListener {
                //しょり
                prefecture = area_List[position] as String
                Log.d("hoge", "prefecture: $prefecture")

                path += prefecture
                firedb_class(view, context).get_city()
            }

        }else{
            val area_data = area_List[position] as Map<String, Any>
            area_title = area_data["title"] as String

            holder.area_text.text = area_title
            holder.view.setOnClickListener {
                //しょり

            }
        }

    }

    override fun getItemCount(): Int {
        return area_List.size
    }

    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, clickedText: String)
    }

    // Itemを削除する
    private fun removeItem(position: Int) {
        area_List.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged() // これを忘れるとRecyclerViewにItemが反映されない
    }

}