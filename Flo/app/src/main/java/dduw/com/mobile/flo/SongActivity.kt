package dduw.com.mobile.flo

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import dduw.com.mobile.flo.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {

    lateinit var binding: ActivitySongBinding
    private lateinit var db: AppDatabase
    private var song: Song = Song()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)
        initSong()
        setupUI()
        setupListeners()
    }

    private fun initSong() {
        val songId = intent.getIntExtra("songId", 1) // 기본값은 첫 번째 곡
        song = db.songDao().getSongById(songId) ?: Song() // RoomDB에서 곡 데이터 가져오기
    }

    private fun setupUI() {
        binding.songMusicTitleTv.text = song.title
        binding.songSingerNameTv.text = song.singer
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)
        binding.songProgressSb.progress = (song.second * 100000) / song.playTime

        setupMediaPlayer()
        updateLikeButton()
    }

    private fun setupMediaPlayer() {
        try {
            val music = resources.getIdentifier(song.music, "raw", this.packageName)
            mediaPlayer = MediaPlayer.create(this, music)
            setPlayerStatus(song.isPlaying)
        } catch (e: Exception) {
            Log.e("SongActivity", "Error setting up MediaPlayer: ${e.message}")
        }
    }

    private fun setupListeners() {
        binding.songDownIb.setOnClickListener { finish() }

        binding.songMiniplayerIv.setOnClickListener { setPlayerStatus(true) }
        binding.songPauseIv.setOnClickListener { setPlayerStatus(false) }

        binding.songLikeIv.setOnClickListener {
            song.isLike = !song.isLike
            db.songDao().update(song) // DB 업데이트
            updateLikeButton()
        }

        binding.songNextIv.setOnClickListener { playNextSong() }
        binding.songPreviousIv.setOnClickListener { playPreviousSong() }
    }

    private fun updateLikeButton() {
        binding.songLikeIv.setImageResource(
            if (song.isLike) R.drawable.ic_my_like_on else R.drawable.ic_my_like_off
        )
    }

    private fun playNextSong() {
        val nextSong = db.songDao().getNextSong(song.id)
        if (nextSong != null) {
            song = nextSong
            saveCurrentSongId(song.id)
            setupUI()
        }
    }

    private fun playPreviousSong() {
        val prevSong = db.songDao().getPreviousSong(song.id)
        if (prevSong != null) {
            song = prevSong
            saveCurrentSongId(song.id)
            setupUI()
        }
    }

    private fun saveCurrentSongId(songId: Int) {
        val sharedPreferences = getSharedPreferences("flo_pref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("songId", songId)
        editor.apply()
    }

    private fun setPlayerStatus(isPlaying: Boolean) {
        song.isPlaying = isPlaying
        if (isPlaying) {
            binding.songMiniplayerIv.visibility = View.GONE
            binding.songPauseIv.visibility = View.VISIBLE
            mediaPlayer?.start()
        } else {
            binding.songMiniplayerIv.visibility = View.VISIBLE
            binding.songPauseIv.visibility = View.GONE
            mediaPlayer?.pause()
        }
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        song.second = ((binding.songProgressSb.progress * song.playTime) / 100) / 1000
        db.songDao().update(song) // DB에 재생 위치 업데이트
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
