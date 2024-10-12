package dduw.com.mobile.flo_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            val songTitle = binding.mainMiniplayerTitleTv.text.toString()
            val songSinger = binding.mainMiniplayerSingerTv.text.toString()

            val intent = Intent(this, SongActivity::class.java).apply {
                putExtra("title", songTitle)
                putExtra("singer", songSinger)
                putExtra("second", 0)
                putExtra("playTime", 60)
                putExtra("isPlaying", false)
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
