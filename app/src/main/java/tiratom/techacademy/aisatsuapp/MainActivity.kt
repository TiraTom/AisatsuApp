package tiratom.techacademy.aisatsuapp

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // 現在時刻を初期値とする
    // 時刻は基本的にHHmmの文字列で扱う。時刻の比較の際は４桁の数として扱う。
    val format = SimpleDateFormat("HHmm", Locale.getDefault())
    var pickedTime: String = format.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ボタンの処理を設定
        button1.setOnClickListener(this)

    }


    override fun onClick(v: View){
        if (v.id == R.id.button1) {
            showTimePickerDialog()
        }

    }


    private fun showTimePickerDialog(){

        var hour: String
        var minute: String

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener() { _, h, m ->
                // 時刻設定後の処理
                hour = "${String.format("%2s", h).replace(" ", "0")}"
                minute = "${String.format("%2s", m).replace(" ", "0")}"

                pickedTime = "$hour$minute"
                Log.d("UI_PARTS", pickedTime)

                // 設定した時刻に応じて挨拶の内容を変える
                // MEMO: hourで比較すればよい。もしxx:yyなど分が条件に関わってくる場合は、分単位に直して比較する方法もある                                  
                when(pickedTime.toInt()){
                    in 0..159 -> textView1.text = "こんばんは"
                    in 200..959 -> textView1.text = "おはよう"
                    in 1000..1759 -> textView1.text = "こんにちは"
                    in 1800..2359 -> textView1.text = "こんばんは"
                }

            },

            pickedTime.substring(0, 2).toInt(),
            pickedTime.substring(2,4).toInt(),
            true)

            timePickerDialog.show()
    }
}
