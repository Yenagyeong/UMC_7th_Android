package dduw.com.mobile.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(private val albumList: List<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumImage: ImageView = itemView.findViewById(R.id.album_image)
        val albumTitle: TextView = itemView.findViewById(R.id.album_title)
        val albumArtist: TextView = itemView.findViewById(R.id.album_artist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.albumTitle.text = album.title
        holder.albumArtist.text = album.singer
        // You can use Glide or Picasso to load images from resources or URLs
        // holder.albumImage.setImageResource(album.coverImg)
    }

    override fun getItemCount(): Int = albumList.size
}
