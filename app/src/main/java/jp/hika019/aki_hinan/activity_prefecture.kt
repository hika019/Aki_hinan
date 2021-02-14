package jp.hika019.aki_hinan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_prefecture.view.*


class activity_prefecture: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_prefecture, container, false)

        val this_context = getContext()
        view.recyclervierw_prefecture.addItemDecoration(DividerItemDecoration(this_context!!, DividerItemDecoration.VERTICAL))

        firedb_class(view, this_context).get_prefecture()

        return view
    }



}