package es.voghdev.architecturesample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import es.voghdev.architecturesample.usecase.Result
import es.voghdev.architecturesample.usecase.SomeUseCase
import es.voghdev.architecturesample.viewstate.MainViewState

class MainViewModel(
    private val someUseCase: SomeUseCase
) : BaseViewModel() {
    private val _viewState = MutableLiveData<MainViewState>(MainViewState.Loading)
    val viewState: LiveData<MainViewState> = _viewState

    init {
        addToDisposable(someUseCase.invoke().subscribe(::handleUseCaseResult))
    }

    private fun handleUseCaseResult(result: Result<Unit>) {
        _viewState.value = MainViewState.ContentLoaded
    }
}