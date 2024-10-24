package es.voghdev.architecturesample.usecase

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

sealed class Result<T> {
    data class Success<T>(val value: T) : Result<T>()
    data class Failure<T>(val throwable: Throwable) : Result<T>()
}

fun <T : Any> Single<T>.dispatch(): Single<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.dispatch(): Observable<T> =
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T : Any> Observable<T>.toResult(): Observable<Result<T>> =
    this.map<Result<T>> { Result.Success(it) }
        .onErrorReturn { exception ->
            Result.Failure(exception)
        }

fun <T : Any> Single<T>.toResult(): Single<Result<T>> =
    this.map<Result<T>> { Result.Success(it) }
        .onErrorReturn { exception ->
            Result.Failure(exception)
        }