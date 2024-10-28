package dduw.com.mobile.memo

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMemo: EditText
    private lateinit var buttonNext: Button
    private var memoText: String? = null // 전역 변수로 메모 내용을 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // XML 레이아웃 설정

        editTextMemo = findViewById(R.id.editTextMemo)
        buttonNext = findViewById(R.id.buttonNext)

        buttonNext.setOnClickListener {
            val memo = editTextMemo.text.toString()
            if (memo.isNotBlank()) {
                val intent = Intent(this, MemoViewActivity::class.java)
                intent.putExtra("MEMO_TEXT", memo)
                startActivity(intent)
            } else {
                Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // onPause에서 저장한 내용이 있다면 EditText에 설정
        memoText?.let {
            editTextMemo.setText(it)
        }
    }

    override fun onPause() {
        super.onPause()
        // 현재까지 작성한 내용을 저장
        memoText = editTextMemo.text.toString()
    }

    override fun onRestart() {
        super.onRestart()
        // 다시 작성할지 묻는 다이얼로그 띄우기
        AlertDialog.Builder(this)
            .setMessage("다시 작성하시겠습니까?")
            .setPositiveButton("네") { _, _ ->
                // 아무 작업도 하지 않음 (기존 내용 유지)
            }
            .setNegativeButton("아니요") { _, _ ->
                // onPause에서 저장했던 내용 초기화
                memoText = null
                editTextMemo.setText("")
            }
            .show()
    }
}
