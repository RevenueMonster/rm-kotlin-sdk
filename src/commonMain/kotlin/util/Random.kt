package org.rm.sdk.util

private val alphabet: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun randomString(size: Int): String {
    return List(size) { alphabet.random() }.joinToString("")
}
