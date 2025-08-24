package com.jar.app.domain.usecase

import api.OnBoardingAPI
import apiModels.ManualBuyEducationWrapper
import service.models.APIResult

class GetOnBoardingDataUseCase(
    private val onBoardingAPI: OnBoardingAPI
) {

    suspend operator fun invoke(): APIResult<ManualBuyEducationWrapper> {
        return onBoardingAPI.getOnBoardingData()
    }
}