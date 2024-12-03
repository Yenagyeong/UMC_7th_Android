package dduw.com.mobile.naver_login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import dduw.com.mobile.naver_login.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View Binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 네이버 로그인 SDK 초기화
        NaverIdLoginSDK.initialize(
            this,
            getString(R.string.naver_client_id),
            getString(R.string.naver_client_secret),
            getString(R.string.naver_client_name)
        )

        // 네이버 로그인 버튼 클릭 리스너
        binding.buttonOAuthLoginImg.setOAuthLogin(object : OAuthLoginCallback {
            override fun onSuccess() {
                // 로그인 성공 처리
                val accessToken = NaverIdLoginSDK.getAccessToken()
                Log.d("NaverLogin", "AccessToken: $accessToken")
                Toast.makeText(this@MainActivity, "로그인 성공!", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(httpStatus: Int, message: String) {
                // 로그인 실패 처리
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Log.e("NaverLogin", "ErrorCode: $errorCode, ErrorDesc: $errorDescription")
                Toast.makeText(this@MainActivity, "로그인 실패: $message", Toast.LENGTH_SHORT).show()
            }

            override fun onError(errorCode: Int, message: String) {
                // 로그인 중 에러 처리
                onFailure(errorCode, message)
            }
        })
    }
}
