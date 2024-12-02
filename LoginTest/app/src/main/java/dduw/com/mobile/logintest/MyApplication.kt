package dduw.com.mobile.logintest

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, "b746ed26f5bf5ff3a7dba0d8ad9a5e8b")
    }
}
