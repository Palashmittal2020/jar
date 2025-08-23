package api

import apiModels.ManualBuyEducationWrapper
import service.models.APIResult

interface OnBoardingAPI {
    suspend fun getOnBoardingData(): APIResult<ManualBuyEducationWrapper>
}