package io.revenuemonster.sdk

import kotlinx.coroutines.runBlocking
import org.junit.Test

class RMSDKTEST {

    @Test
    fun test(){
        val auth = Config(
            clientID = "1623743073701188526",
            clientSecret = "TZqprtCpGAhagCyDTFiqigAfIFjPOKHY",
            privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                    "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPdfakYApwp0kz\n" +
                    "uTTb1EzvmM39HWnlwfrKkmpt+alYkV3h9rT5HKoRHCRewAzRFXYm5vV1srHrXBfc\n" +
                    "bFJeZnd+Os6Bppe8iRQK/QPQVoNwAAJn40ipKcHPrcXZBb2fXaaM69Ff5+b4CSDt\n" +
                    "DOhdiHKhgq7eLqHI7UXWddObpl05c2vY9j/bzpIiVAacgU6gNmVLv2ijaOGKX4lF\n" +
                    "CovLFYRz3a47CBdpsqNo8y/tTwK5j9aTfPhon804Ye+ki5A3xV/0YJFk7R5b/7rv\n" +
                    "BXBi73CR4dMdOMlJMZiP/5sjKtg79XPIbzv7fu2Uborqfil4iekK5CMYwMKpiZwI\n" +
                    "YLRLcdgNAgMBAAECggEARkzFH0mc9BVVzs3cNDk1tleH4ZgvU5KaBfh3xQqJzMf3\n" +
                    "XIqbplNAl3/V5sRvw2mj625ULGfzveT0IGTIfE/0EFxa47eyNYZq1vOjkc1DPXgT\n" +
                    "tbrv2U2IFvANusqQYG7w9a99e2TVjwhSJTelCmiZ28GBVYk+aIq1JWG531VH66WC\n" +
                    "Fwg27FE/YmY21URLZBeoTc6Hiw0EhItImJVBAJIlIqyuLaGC5zeg0WzT2d0hD6E8\n" +
                    "JjAg4MuuJt/DyJWftZFnDEkoc1ZGZFdUl4AulPlvIo3jko+N/XRU8yncETWoe/Rf\n" +
                    "S9BvwyReBgfzI1bpfmsH0krzgmK4oE4A3qGVAFt34QKBgQDT7Vc59G/Iii3uT2zT\n" +
                    "f0UozaQ4QUEPYrj8+Uv8777z2Ek4uUhUernmo39JiwcsUqWcUqrVDvM5Z7T2FiGq\n" +
                    "azWoYp15A/W1erhMYzLWHnfjNjLMSxvOvUM1rayCdovT6Gf+hXWgdbsaZgnI6/zq\n" +
                    "TQ9DkdJ9EXC1PXfG5AqhqDRwqQKBgQCtS5X74SrM+hypt/mOdMTI7Z5pHm83Pjwp\n" +
                    "RtvvjwTss8Q3cHxPw/pW34GFUbdfjxy0NBy/2jtODaxS65yJjZQvIE/pSb131E/V\n" +
                    "i54ByD3GYqJwWZPNosxmssr93bgKmljorWcW5gSbVraArrwyPNgwNCqXrW0HOGDd\n" +
                    "/ji5p2e2xQKBgQCPDxhitu7+oMiApn0ACFnXQXopv2L7z9iwbFkvVWfBk69Z6zk4\n" +
                    "TeBQIO475u3gram97NAFIyJtDeTKegYf1F9UW72Rg2rKJx1h81TN1aN4bY28+ra6\n" +
                    "yGgmRCa/quaZFbmdxtmwh8jV+SuBxkvknkA/jKBabPt4XRYN3MKQfctogQKBgQCc\n" +
                    "0lkBHmvoILLNl5J7qwnltrry77q/aE9zHw+rA2b2eVuou9+TI4upEAwaxK9IB693\n" +
                    "F+2oZGnkoZT/WP5IsFmYKAqI19Vg2j4Axp8Ph9D3dY0AVN/faGLG0SfHgtyoo7Kv\n" +
                    "pTQ0UaV7mYYAf6AJamxNevfMWQHgVdG7MvLMd0Tw7QKBgHuHWOZqU9lj2FtOc9GL\n" +
                    "VdIvNkXGkl/X5p8PU9IiKqf/eTYBxaHj+hvAR6BdOGxF3b5+58cGQGNJFZARscYY\n" +
                    "iePqjUF49nH0qp7Oj0EuTbPV1kF120IKTgiMKkK6Y3FbafKcsz5IUgRD/N5ctG9X\n" +
                    "PQS9CE72OPXdNRogeolCIfdx\n" +
                    "-----END PRIVATE KEY-----\n",
            publicKey = "-----BEGIN RSA PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA5drTGuzwolTC37/yNBKZ\n" +
                    "fszzXWrGBNHtGYQg6f6xkOgx7a6Ke12mbORfIVDnigtbQCXeRfMh5mpiH1C0rZxo\n" +
                    "iV0FMjlTXV2r4YUle/qgWZbonjsNbNMVRrVzwtHuVdBu7z6Bhk9K4CaNxhjeGpCZ\n" +
                    "C9nEPeQ23IzeQRNuZLZ5/VdRECd6H9pgojP6CqI+RkGo0Wat4ifqQTJ59HAjlZFo\n" +
                    "CuBCXUwU3ppJAwrDVhzMnH5aaEznROnhLl+eOsNNdtV7KEU7OuuCE1fOoYTfa0ko\n" +
                    "2q3RnJo3ZIQO0RHTOF3L/WfZHesgeXFrtIRW9mPsFKJ2xHrcOiXl6F9QoNNdnf9k\n" +
                    "TwIDAQAB\n" +
                    "-----END RSA PUBLIC KEY-----\n",
            sandbox = true
        )


        val sdk = RevenueMonsterSDK(auth)

        runBlocking {
            try {

                val result = sdk.Store.getStores()
                println("Result ====>")
                println(result)

            }catch (e: Error) {
                println("Debug 1 ====>")
                println(e)
            }catch (e: Throwable) {
                println("Debug 2 ====>")
                println(e)
            }
        }
    }


}