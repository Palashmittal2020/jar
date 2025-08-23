package apiImpl

import api.OnBoardingAPI
import apiModels.ManualBuyEducationWrapper
import io.ktor.client.HttpClient
import service.getRequest
import service.models.APIResult

internal class OnBoardingAPIImpl(
    private val client: HttpClient
) : OnBoardingAPI {

    override suspend fun getOnBoardingData(): APIResult<ManualBuyEducationWrapper> =
        client.getRequest(END_POINT)


    companion object {
        private const val END_POINT = "_assets/shared/education-metadata.json"
    }

}