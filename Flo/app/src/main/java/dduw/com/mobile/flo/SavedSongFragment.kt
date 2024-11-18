package dduw.com.mobile.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dduw.com.mobile.flo.databinding.FragmentLockerSavedsongBinding

class SavedSongFragment : Fragment() {

    private lateinit var binding: FragmentLockerSavedsongBinding
    private lateinit var songAdapter: SongAdapter
    private val songList = mutableListOf<Song>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLockerSavedsongBinding.inflate(inflater, container, false)

        // RecyclerView 초기화
        binding.lockerSavedSongRecyclerView.layoutManager = LinearLayoutManager(context)
        songAdapter = SongAdapter(songList) { song ->
            // 좋아요 클릭 이벤트 처리
            song.isLike = !song.isLike
            songAdapter.notifyDataSetChanged() // 리스트 갱신
        }
        binding.lockerSavedSongRecyclerView.adapter = songAdapter

        // 더미 데이터 추가
        addDummyData()

        return binding.root
    }

    private fun addDummyData() {
        songList.add(Song(1, "Butter", "방탄소년단", 0, 180, false, false, "music_butter", R.drawable.img_album_exp))
        songList.add(Song(2, "라일락", "아이유", 0, 200, false, false, "music_lilac", R.drawable.img_album_exp2))
        songList.add(Song(3, "Next Level", "에스파", 0, 210, false, false, "music_next_level", R.drawable.img_album_exp3))
        songList.add(Song(4, "작은 것들을 위한 시", "방탄소년단", 0, 180, false, false, "music_smallthings", R.drawable.img_album_exp4))
        songList.add(Song(5, "BAMM", "모모랜드", 0, 190, false, false, "music_bamm", R.drawable.img_album_exp5))
        songList.add(Song(6, "Weekend", "태연", 0, 240, false, false, "music_weekend", R.drawable.img_album_exp6))
        songList.add(Song(7, "Hype Boy", "뉴진스", 0, 220, false, false, "music_hypeboy", R.drawable.img_album_exp7))
        songList.add(Song(8, "ETA", "뉴진스", 0, 230, false, false, "music_eta", R.drawable.img_album_exp8))
        songList.add(Song(9, "Hi Bully", "터치드", 0, 210, false, false, "music_hibully", R.drawable.img_album_exp9))
        songList.add(Song(10, "항해", "유다빈밴드", 0, 250, false, false, "music_sailing", R.drawable.img_album_exp10))
        songAdapter.notifyDataSetChanged()
    }
}
