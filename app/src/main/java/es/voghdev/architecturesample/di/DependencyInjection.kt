package es.voghdev.architecturesample.di

import es.voghdev.architecturesample.usecase.SomeUseCase
import es.voghdev.architecturesample.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
viewModel { MainViewModel(get()) }
    single { SomeUseCase() }
}

val koinModules = listOf(applicationModule)
