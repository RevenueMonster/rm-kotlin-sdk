package io.revenuemonster.sdk

import io.revenuemonster.sdk.model.auth.Config
import io.revenuemonster.sdk.util.RMException
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AndroidSDK {

    @Test
    fun test(){

        val config2 = Config(
            clientID = "1662954961551527171",
            clientSecret = "IQUMlKIzfxbWUIDxSQsggHaplFuSxkkX",
            privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                    "MIIEowIBAAKCAQB56wg/6BI9zmKDoaJcgiz9gDU3l2/NA3LsJZTVLveuTzl06i5h\n" +
                    "Be3xscQRJIm+aIDdhvs2CDOKKMZ4x8FaDAWC8YA6MIMfHSg7tOnmBM2Lae/kmoqd\n" +
                    "Z8RzUpjgy6lwcDk8Jo/NjhTFCaVJDpeGJrUQc3fB7UJskoM1efX8NFMPS2S/n97A\n" +
                    "s0ZYiCMAIzN86UchR1wZFF0VvfEzuXtbfc0Enq03Yo2KKPRIeLo6beyfZ9mfdqCl\n" +
                    "hhOMG3cuJKSG+t08JAdtkAj6DejQxZuH6MpN4MDtN9xwI5a/7BKJZ9ORCvGszWF6\n" +
                    "r7SyKn/PO/sYF6ysINJUOlGjthZo280K/BSVAgMBAAECggEAaZpy3+AmBNFDNd+I\n" +
                    "4naDf+VQs2GCHuPQ5Mm8vFfdhGftTd8W40WekI85VNwAUCT3Emu8J1Y9RnwBRldI\n" +
                    "nLXzBDQprL2ephG8atNPEsWwQQU3HR3b2XIrkPTHpVfj4Zfji72fLB7Y0L+FIt33\n" +
                    "0+StS52o6xqXQbI64tRMhPPRyll9rxuQJGj29oJgtkPoHfF2fdnlYveQS5xAJuaN\n" +
                    "kLd0MkVUf3ComT3630nEhas6mZO9+hO+fnaJWyj1hupCFMQQ8TBxNw/gEZdCa9um\n" +
                    "oPEvrhCUNdJYEvkfnQIffMuxpv5GBz5ZBVNFH2YCtiq1eTPkFRPlh9AEDB1huA9U\n" +
                    "KZjPAQKBgQDUIcdsYApEvzaHJDScEjqLBuO3t7KK3jpp+uC/WgeSWFAGe0XsCefH\n" +
                    "GjIVIjUG2aiA9r7Yd9+uaIhRT19iWH3l+pD6j2EdJyS1Jnvn7/KxXrvKsnGmY5Dq\n" +
                    "DeKrlc8i2XxDuI4WVc5nxGmlSX2JZPg+napGF/wXpLrBYV3ywAPIVQKBgQCTIVnD\n" +
                    "xcjMNPWPpOAQaokfSqwkk8KArB9GZR1jLFSh8hG/1i1OsxSUdbg7Cfa9Vc6b6Zc0\n" +
                    "waulJ61a4JkRf+sf6yrkrhZbQWoK9d+/1ISU9lcz5uRc+sbk0nZfE/rBYbK+QRJ4\n" +
                    "hrmTzv8wrukl0zUWqfTR8m5Txc1ERhzROpBbQQKBgGkpSbyPK+gCxtxd9Xq9iqor\n" +
                    "Qy5rcaZoHps1VL9nuUMyZpGgAPzPuEg600MozxLTSjaMm/WiD5Bdn953M7kEV31w\n" +
                    "JvkfpSwXoJnxNxG3ISAO75e/uJmc2FU/hvQ68xG6sWC9GCs4ZHeKpjjbYtF+7ZXV\n" +
                    "AXl4IG/0KOT/CNzzlax1AoGBAIMBJR5OU7aQST43vlKfqay1HoYLExrjJ43UptAR\n" +
                    "PuJb2jYIDJuQlPiDwSDAhHSdnLPyz/HnqGAL7i2G4oRUdBUdnjm0UK+8lSFhQHNc\n" +
                    "VRkWJxtNsXSgzswcIc4Syim6Rh9lbX7n5wGXRF4iv/IwF4swlY8C3/2zmEv8adha\n" +
                    "3wKBAoGBALTZbhNXwin3q1IvH7ZzYQdyBmiSlrcu5lHn9fpc+uLIGMeU/v2ynaE1\n" +
                    "pseI1FOdK9Nz21gFt0Y/jsgKQuIzXHftA/1HHmU4jxMxN54KEUTAewlIUuoJKCOK\n" +
                    "7du7iRYkSCo2ZVayfga0FpKqSpV1XzzcB3uySBP8XGpzAZ3dHfMn\n" +
                    "-----END RSA PRIVATE KEY-----",
            sandbox = true
        )

        runBlocking {
            try {

                val auth = RevenueMonsterAuth(config2).getAccessToken()
                val sdk = RevenueMonsterSDK(auth)
                val result = sdk.payment.getTransactions()
                println("Result ====>")
                println(result)

            }catch (e: RMException) {
                println("Debug 1 ====>")
                println(e.errorCode)
                println(e.errorMessage)
            }catch (e: Throwable) {
                println("Debug 2 ====>")
                println(e)
            }
        }
    }

}