package com.jar.app.di

import com.jar.app.presentation.onBoarding.OnBoardingScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::OnBoardingScreenViewModel)
}