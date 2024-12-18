package dduw.com.mobile.flo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlbumDao {
    @Insert
    suspend fun insert(album: Album)

    @Update
    suspend fun update(album: Album)

    @Delete
    suspend fun delete(album: Album)

    @Query("SELECT * FROM AlbumTable")
    suspend fun getAlbums(): List<Album>

    @Insert
    suspend fun likeAlbum(like: Like)

    @Query("SELECT id FROM LikeTable WHERE userId = :userId AND albumId = :albumId;")
    suspend fun isLikedAlbum(userId: Int, albumId: Int): Int?

    @Query("DELETE FROM LikeTable WHERE userId = :userId AND albumId = :albumId;")
    suspend fun disLikedAlbum(userId: Int, albumId: Int)

    @Query("SELECT AT.* FROM LikeTable as LT LEFT JOIN AlbumTable as AT ON LT.albumId = AT.id WHERE LT.userId = :userId")
    suspend fun getLikedAlbums(userId: Int): List<Album>
}
