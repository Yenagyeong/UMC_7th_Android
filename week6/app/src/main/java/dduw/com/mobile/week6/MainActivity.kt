package dduw.com.mobile.week6

import SimpleListFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SimpleListFragment를 MainActivity에 추가
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SimpleListFragment())
            .commit()
    }
}