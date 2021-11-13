package com.training.miniproject.util

sealed class Either<out E, out V> {
    data class Error<out E>(val error: E) : Either<E, Nothing>()
    data class Value<out V>(val value: V) : Either<Nothing, V>()
}

suspend fun <V> either(block: suspend () -> V): Either<Throwable, V> = runCatching {
    Either.Value(block())
}.getOrElse { Either.Error(it) }

fun <V> Either<Any, V>.getOrNull(): V? = when (this) {
    is Either.Value -> this.value
    is Either.Error -> null
}
