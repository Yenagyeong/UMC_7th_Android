package dduw.com.mobile.flo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlbumTable")
data class Album(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "singer") var singer: String = "",
    @ColumnInfo(name = "coverImg") var coverImg: Int? = null
) {
    constructor() : this(0, "", "", null)
}
