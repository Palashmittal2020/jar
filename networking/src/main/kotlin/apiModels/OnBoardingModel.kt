package apiModels

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val data: T
)

@Serializable
data class ManualBuyEducationWrapper(
    val manualBuyEducationData: ManualBuyEducationData
)

@Serializable
data class ManualBuyEducationData(
    val toolBarText: String,
    val introTitle: String,
    val introSubtitle: String,
    val educationCardList: List<EducationCard>,
    val saveButtonCta: SaveButtonCta,
    val ctaLottie: String,
    val screenType: String,
    val cohort: String,
    val combination: String? = null,
    val collapseCardTiltInterval: Long,
    val collapseExpandIntroInterval: Long,
    val bottomToCenterTranslationInterval: Long,
    val expandCardStayInterval: Long,
    val seenCount: Int? = null,
    val actionText: String,
    val shouldShowOnLandingPage: Boolean,
    val toolBarIcon: String,
    val introSubtitleIcon: String,
    val shouldShowBeforeNavigating: Boolean
)

@Serializable
data class EducationCard(
    val image: String,
    val collapsedStateText: String,
    val expandStateText: String,
    val backGroundColor: String,
    val strokeStartColor: String,
    val strokeEndColor: String,
    val startGradient: String,
    val endGradient: String
)

@Serializable
data class SaveButtonCta(
    val text: String,
    val deeplink: String? = null,
    val backgroundColor: String,
    val textColor: String,
    val strokeColor: String,
    val icon: String? = null,
    val order: Int? = null
)
