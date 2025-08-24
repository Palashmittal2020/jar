package com.jar.app.domain.models

import android.graphics.Bitmap
import apiModels.SaveButtonCta

data class ManualBuyEducationDataUiModel(
    val toolBarText: String,
    val introTitle: String,
    val introSubtitle: String,
    val educationCardList: List<EducationCardUiModel>,
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
    val toolBarIcon: Bitmap?,
    val introSubtitleIcon: Bitmap?,
    val shouldShowBeforeNavigating: Boolean
)

data class EducationCardUiModel(
    val image: Bitmap?,
    val collapsedStateText: String,
    val expandStateText: String,
    val backGroundColor: String,
    val strokeStartColor: String,
    val strokeEndColor: String,
    val startGradient: String,
    val endGradient: String
)