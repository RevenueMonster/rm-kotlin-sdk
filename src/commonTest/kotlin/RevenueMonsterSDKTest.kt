package org.rm.sdk

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.test.Test
import kotlin.test.assertEquals

class RevenueMonsterSDKTest {
    @Test
    fun initSDK() {
        val sdk = RevenueMonsterSDK()
        assertEquals("test", sdk.getString())
        CoroutineScope(Dispatchers.Main).launch {
            sdk.payment.qrPay()
        }
    }
}
