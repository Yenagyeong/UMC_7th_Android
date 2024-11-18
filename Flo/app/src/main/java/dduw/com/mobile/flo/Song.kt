package dduw.com.mobile.flo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SongTable")
data class Song(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,                // Room에서 관리할 Primary Key
    var title: String = "",         // 노래 제목
    var singer: String = "",        // 가수 이름
    var second: Int = 0,            // 현재 재생 시간 (초)
    var playTime: Int = 0,          // 총 재생 시간 (초)
    var isPlaying: Boolean = false, // 현재 재생 여부
    var isLike: Boolean = false,    // 좋아요 여부
    var music: String = "",         // 음악 파일 이름 (raw 리소스)
    var imageResId: Int = 0,        // 앨범 이미지 리소스 ID
    var albumIdx: Int = 0           // Album Table의 Primary Key를 참조
)
