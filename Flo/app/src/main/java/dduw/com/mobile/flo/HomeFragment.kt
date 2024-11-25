package dduw.com.mobile.flo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import dduw.com.mobile.flo.databinding.FragmentAlbumBinding
import dduw.com.mobile.flo.databinding.FragmentHomeBinding
import java.util.ArrayList
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val timer = Timer()
    private val handler = Handler(Looper.getMainLooper())

    //    private var albumDatas = ArrayList<Album>()
    private var albumDatas = ArrayList<Album>()
    private lateinit var songDB : SongDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        songDB = SongDatabase.getInstance(requireContext())!!

        CoroutineScope(Dispatchers.IO).launch {
            val albums = songDB.albumDao().getAlbums()

            withContext(Dispatchers.Main) {
                albumDatas.addAll(albums)

                // RecyclerView 어댑터 설정
                val albumRVAdapter = AlbumRVAdapter(albumDatas)
                binding.homeTodayMusicAlbumRv.adapter = albumRVAdapter
                binding.homeTodayMusicAlbumRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                albumRVAdapter.setMyItemClickListener(object: AlbumRVAdapter.MyItemClickListener {
                    override fun onItemClick(album: Album) {
                        (context as MainActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm , AlbumFragment().apply {
                                arguments = Bundle().apply {
                                    val gson = Gson()
                                    val albumJson = gson.toJson(album)
                                    putString("album", albumJson)
                                }
                            })
                            .commitAllowingStateLoss()
                    }
                })

                albumRVAdapter.setMiniPlayerSyncListener(object: AlbumRVAdapter.MiniPlayerSyncListener {
                    override fun onPlayButtonClick(album: Album) {
                        (context as MainActivity).updateMiniPlayer(album)
                    }
                })
            }
        }

//        // banner viewPager
        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_home_viewpager_exp2))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homeBannerIndicator.setViewPager(binding.homeBannerVp)

        autoSlide(bannerAdapter)

        // pannel viewPager
        val pannelAdapter = PannelVPAdapter(this)
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))
        pannelAdapter.addFragment(PannelFragment(R.drawable.img_first_album_default))

        binding.homePannelBackgroundVp.adapter = pannelAdapter
        binding.homePannelBackgroundVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.homePannelIndicator.setViewPager(binding.homePannelBackgroundVp)

        return binding.root
    }

    private fun autoSlide(adapter: BannerVPAdapter) {
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post {
                    val nextItem = binding.homeBannerVp.currentItem + 1
                    if (nextItem < adapter.itemCount) {
                        binding.homeBannerVp.currentItem = nextItem
                    } else {
                        binding.homeBannerVp.currentItem = 0 // 순환
                    }
                }
            }
        }, 3000, 3000)
    }
}