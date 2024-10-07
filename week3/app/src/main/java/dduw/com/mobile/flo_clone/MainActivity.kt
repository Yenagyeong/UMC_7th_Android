package dduw.com.mobile.flo_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log // 로그 추가
import dduw.com.mobile.flo_clone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BottomNavigation 초기화
        initBottomNavigation()

        // 미니 플레이어 클릭 시 SongActivity로 이동하며, 곡 정보 전달
        binding.mainPlayerCl.setOnClickListener {
            val title = binding.mainMiniplayerTitleTv.text?.toString() ?: "Unknown Title"
            val singer = binding.mainMiniplayerSingerTv.text?.toString() ?: "Unknown Artist"

            Log.d("MainActivity", "Mini player clicked, Title: $title, Singer: $singer") // 디버깅 로그 추가

            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("title", title)
                putExtra("singer", singer)
            }

            startActivity(intent)
        }
    }

    private fun initBottomNavigation() {
        // 기본 Fragment를 HomeFragment로 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commitAllowingStateLoss()

        // Bottom Navigation 아이템 클릭 리스너 설정
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lookFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}
