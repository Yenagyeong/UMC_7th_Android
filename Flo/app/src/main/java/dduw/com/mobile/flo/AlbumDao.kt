package dduw.com.mobile.flo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlbumDao {
    @Insert
    fun insert(album: Album)

    @Insert
    fun insertAll(albums: List<Album>)

    @Query("SELECT * FROM AlbumTable")
    fun getAllAlbums(): List<Album>

    @Query("SELECT * FROM AlbumTable WHERE id = :albumId")
    fun getAlbumById(albumId: Int): Album?
}
