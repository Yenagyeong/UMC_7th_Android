package dduw.com.mobile.flo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AlbumTable")
data class Album(
    @PrimaryKey val id: Int,
    val title: String,
    val singer: String,
    val coverImg: String // 이미지 경로 혹은 리소스 ID로 사용할 수 있음
)
