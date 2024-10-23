package es.voghdev.architecturesample.usecase

import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit

class SomeUseCase {
    operator fun invoke() : Single<Result<Unit>> =
        Single.just(Unit)
            .delay(3000, TimeUnit.MILLISECONDS)
            .dispatch()
            .toResult()
}
