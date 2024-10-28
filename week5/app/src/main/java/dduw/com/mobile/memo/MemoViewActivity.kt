package dduw.com.mobile.memo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemoViewActivity : AppCompatActivity() {
    private lateinit var textViewMemo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_view)

        textViewMemo = findViewById(R.id.textViewMemo)
        // 메모 화면에서 작성한 내용을 받아와 TextView에 표시
        val memoText = intent.getStringExtra("MEMO_TEXT")
        textViewMemo.text = memoText
    }
}
