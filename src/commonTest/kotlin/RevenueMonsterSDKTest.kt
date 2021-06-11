package org.rm.sdk

// import kotlinx.coroutines.CoroutineScope
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class RevenueMonsterSDKTest {
    @Test
    fun initSDK() {
        val sdk = RevenueMonsterSDK()

        runBlocking {
            val result = sdk.payment.qrPay()
            println(result)
        }

        println(sdk.getString())
//        CoroutineScope(Dispatchers.Main).launch {

//        }
    }
}
