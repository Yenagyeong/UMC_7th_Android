package dduw.com.mobile.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(
    private val songList: List<Song>,
    private val onLikeClick: (Song) -> Unit // 좋아요 버튼 클릭 콜백 추가
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val songTitle: TextView = itemView.findViewById(R.id.item_song_title_tv)
        val songArtist: TextView = itemView.findViewById(R.id.item_song_singer_tv)
        val songImage: ImageView = itemView.findViewById(R.id.item_song_img_iv)
        val likeButton: ImageView = itemView.findViewById(R.id.song_like_iv) // 좋아요 버튼
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songList[position]
        holder.songTitle.text = song.title
        holder.songArtist.text = song.singer
        holder.songImage.setImageResource(song.imageResId)

        // 좋아요 상태에 따라 아이콘 변경
        holder.likeButton.setImageResource(
            if (song.isLike) R.drawable.ic_my_like_on else R.drawable.ic_my_like_off
        )

        // 좋아요 버튼 클릭 이벤트
        holder.likeButton.setOnClickListener {
            onLikeClick(song) // 콜백 호출
        }
    }

    override fun getItemCount(): Int = songList.size
}
