package dduw.com.mobile.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dduw.com.mobile.flo_clone.databinding.FragmentAlbumBinding

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달된 데이터를 가져와서 UI에 반영
        val albumTitle = arguments?.getString("albumTitle") ?: "Unknown Title"
        val albumArtist = arguments?.getString("albumArtist") ?: "Unknown Artist"
        binding.albumTitleTextView.text = albumTitle
        binding.albumArtistTextView.text = albumArtist

        // 뒤로 가기 버튼 클릭 시 HomeFragment로 돌아감
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
