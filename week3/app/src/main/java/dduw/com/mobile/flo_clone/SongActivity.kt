package dduw.com.mobile.flo_clone

import android.os.Bundle
import android.util.Log // 로그 추가
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.flo_clone.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 전달된 데이터를 SongActivity에서 표시
        val title = intent.getStringExtra("title") ?: "Unknown Title"
        val singer = intent.getStringExtra("singer") ?: "Unknown Artist"

        Log.d("SongActivity", "Song title: $title, Singer: $singer") // 디버깅 로그 추가

        binding.songTitle.text = title
        binding.artistName.text = singer

        // Down 버튼 클릭 시 MainActivity로 돌아감
        binding.songDownIb.setOnClickListener {
            Log.d("SongActivity", "Down button clicked, finishing activity") // 디버깅 로그 추가
            finish()
        }
    }
}
