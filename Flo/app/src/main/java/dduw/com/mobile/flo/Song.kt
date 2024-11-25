package dduw.com.mobile.flo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "SongTable")
data class Song(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "albumIdx") var albumIdx: Int = 0,
    @ColumnInfo(name = "isLike") var isLike: Boolean = false,
    @ColumnInfo(name = "playTime") var playTime: Int = 0,
    @ColumnInfo(name = "coverImg") var coverImg: String = ""
) {
    constructor() : this(0, "", 0, false, 0, "")
}
