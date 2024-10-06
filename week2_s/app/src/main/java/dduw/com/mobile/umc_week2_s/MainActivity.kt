package dduw.com.mobile.umc_week2_s

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dduw.com.mobile.umc_week2_s.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    setFrag(0)
                }
                R.id.action_write -> {
                    setFrag(1)
                }
                R.id.action_calendar -> {
                    setFrag(2)
                }
                else -> {
                    false
                }
            }
        }
        setFrag(0)
    }

    private fun setFrag(fragNum : Int) : Boolean {
        val ft = supportFragmentManager.beginTransaction()

        when (fragNum) {
            0 -> {
                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                ft.replace(R.id.main_frame, FragHome())
            }
            1 -> {
                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                ft.replace(R.id.main_frame, FragWrite())
            }
            2 -> {
                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                ft.replace(R.id.main_frame, FragCalendar())
            }
        }
        ft.commit()
        return true
    }
}
