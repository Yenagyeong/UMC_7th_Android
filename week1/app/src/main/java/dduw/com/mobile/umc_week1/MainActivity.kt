package dduw.com.mobile.umc_week1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttons = listOf(
            R.id.expln1_pic,
            R.id.expln2_pic,
            R.id.expln3_pic,
            R.id.expln4_pic,
            R.id.expln5_pic
        )

        buttons.forEach { buttonId ->
            val button: ImageButton = findViewById(buttonId)
            button.setOnClickListener {
                val intent = Intent(this, EmotionActivity::class.java)
                startActivity(intent)
            }
        }
    }

}