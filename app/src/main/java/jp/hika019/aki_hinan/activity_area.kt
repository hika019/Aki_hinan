package jp.hika019.aki_hinan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_area.*

class activity_area : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_area)


        hokkaido_textView.setOnClickListener{
            //北海道

        }
        touhoku_textview.setOnClickListener{
            //東北

        }
        hokuriku_textview.setOnClickListener{
            //北陸

        }
        kantou_textView.setOnClickListener{
            //関東

        }
        toukai_textView.setOnClickListener{
            //東海

        }








    }
}