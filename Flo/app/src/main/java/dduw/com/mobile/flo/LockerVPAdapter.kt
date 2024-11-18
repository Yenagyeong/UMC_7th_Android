package dduw.com.mobile.flo

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class LockerVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3  // 페이지 수

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SavedAlbumFragment()  // 앨범 저장 Fragment
            1 -> SavedSongFragment()   // 저장된 곡 리스트를 담은 RecyclerView Fragment
            else -> MusicFileFragment()  // 미디어 파일 Fragment
        }
    }
}
