package org.rm.sdk

// import kotlinx.coroutines.CoroutineScope
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class RevenueMonsterSDKTest {
    @Test
    fun initSDK() {
        val sdk = RevenueMonsterSDK(
            clientID = "1599646279297591629",
            clientSecret = "NekiDbnNHbHLWdRmbqtwBCqywfYkVVnE",
            privateKey = "",
            publicKey = "",
        )

        runBlocking {
            sdk.getAccessToken()
//            sdk.payment.find("210612084000300428219453")
//            val result = sdk.payment.qrPay()
//            println(result)
        }

        println(sdk.getString())
//        CoroutineScope(Dispatchers.Main).launch {

//        }
    }
}
