package es.voghdev.architecturesample.viewstate

sealed class MainViewState {
    object Loading: MainViewState()
    object ContentLoaded: MainViewState()
    object Error: MainViewState()
}
