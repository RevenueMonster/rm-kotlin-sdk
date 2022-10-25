package io.revenuemonster.sdk.util
fun randomString(size: Int): String {
    val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return List(size) { alphabet.random() }.joinToString("")
}
