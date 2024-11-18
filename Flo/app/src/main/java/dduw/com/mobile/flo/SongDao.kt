package dduw.com.mobile.flo

import androidx.room.*

@Dao
interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(song: Song)

    @Insert
    fun insertAll(songs: List<Song>)

    @Update
    fun update(song: Song)

    @Query("SELECT * FROM SongTable WHERE id = :id")
    fun getSongById(id: Int): Song?

    @Query("SELECT * FROM SongTable")
    fun getAllSongs(): List<Song>

    @Query("SELECT * FROM SongTable WHERE id > :currentId LIMIT 1")
    fun getNextSong(currentId: Int): Song?

    @Query("SELECT * FROM SongTable WHERE id < :currentId ORDER BY id DESC LIMIT 1")
    fun getPreviousSong(currentId: Int): Song?
}
