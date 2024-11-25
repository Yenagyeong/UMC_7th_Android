package dduw.com.mobile.flo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SongDao {
    @Insert
    suspend fun insert(song: Song)

    @Update
    suspend fun update(song: Song)

    @Delete
    suspend fun delete(song: Song)

    @Query("SELECT * FROM SongTable")
    suspend fun getSongs(): List<Song>

    @Query("SELECT * FROM SongTable WHERE id = :id")
    suspend fun getSong(id: Int): Song

    @Query("UPDATE SongTable SET isLike = :isLike WHERE id = :id")
    suspend fun updateIsLikeById(isLike: Boolean, id: Int): Int

    @Query("DELETE FROM SongTable WHERE id = :id")
    suspend fun deleteSongById(id: Int): Int

    @Query("SELECT * FROM SongTable WHERE isLike = :isLike")
    suspend fun getLikedSongs(isLike: Boolean): List<Song>
}
