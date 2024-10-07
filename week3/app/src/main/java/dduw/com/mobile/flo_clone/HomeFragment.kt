package dduw.com.mobile.flo_clone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dduw.com.mobile.flo_clone.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController
import androidx.core.os.bundleOf

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 앨범 클릭 시 AlbumFragment로 데이터 전달
        binding.homePannelAlbumInfoLayout.setOnClickListener {
            val bundle = bundleOf(
                "albumTitle" to "IU 5th Album 'LILAC'",
                "albumArtist" to "아이유 (IU)"
            )
            findNavController().navigate(R.id.action_homeFragment_to_albumFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
