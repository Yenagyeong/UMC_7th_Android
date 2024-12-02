package dduw.com.mobile.logintest

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button
    private lateinit var userInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.btnLogin)
        logoutButton = findViewById(R.id.btnLogout)
        userInfoTextView = findViewById(R.id.tvUserInfo)

        // 로그인 버튼 클릭
        loginButton.setOnClickListener {
            UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
                if (error != null) {
                    userInfoTextView.text = "로그인 실패: ${error.message}"
                } else if (token != null) {
                    userInfoTextView.text = "로그인 성공"
                    fetchUserInfo()
                }
            }
        }

        // 로그아웃 버튼 클릭
        logoutButton.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    userInfoTextView.text = "로그아웃 실패: ${error.message}"
                } else {
                    userInfoTextView.text = "로그아웃 성공"
                    loginButton.isEnabled = true
                    logoutButton.isEnabled = false
                }
            }
        }
    }

    // 사용자 정보 가져오기
    private fun fetchUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                userInfoTextView.text = "사용자 정보 요청 실패: ${error.message}"
            } else if (user != null) {
                val nickname = user.kakaoAccount?.profile?.nickname
                val email = user.kakaoAccount?.email
                val profileImageUrl = user.kakaoAccount?.profile?.profileImageUrl
                userInfoTextView.text = "닉네임: $nickname\n이메일: $email\n프로필 이미지 URL: $profileImageUrl"

                loginButton.isEnabled = false
                logoutButton.isEnabled = true
            }
        }
    }
}