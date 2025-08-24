package com.jar.app.di

import com.jar.app.domain.usecase.GetOnBoardingDataUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::GetOnBoardingDataUseCase)
}