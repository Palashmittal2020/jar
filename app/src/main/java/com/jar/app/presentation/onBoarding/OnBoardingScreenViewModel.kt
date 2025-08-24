package com.jar.app.presentation.onBoarding

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import apiModels.EducationCard
import apiModels.ManualBuyEducationData
import com.bumptech.glide.Glide
import com.jar.MainApplication
import com.jar.app.domain.models.EducationCardUiModel
import com.jar.app.domain.models.ManualBuyEducationDataUiModel
import com.jar.app.domain.usecase.GetOnBoardingDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import service.models.APIResult

class OnBoardingScreenViewModel() : ViewModel(), KoinComponent {

    private val getOnBoardingDataUseCase: GetOnBoardingDataUseCase by inject()
    private val _selectedCardIndex = MutableStateFlow(0)
    val selectedCardIndex = _selectedCardIndex.asStateFlow()

    private val _toastMessage = Channel<String>()
    val toastMessage = _toastMessage.receiveAsFlow()

    private val _onBoardingData = MutableStateFlow<ManualBuyEducationDataUiModel?>(null)
    val onBoardingData = _onBoardingData.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = getOnBoardingDataUseCase.invoke()

            when (result) {
                is APIResult.ERROR -> {
                    // Log Error to Firebase
                    _toastMessage.send("Something Went Wrong")
                }

                is APIResult.SUCCESS -> {
                    result.data?.data?.manualBuyEducationData?.let {
                        _onBoardingData.value = mapEntityToUiModel(it)
                    }
                }
            }
        }
    }

    fun onSelectedCardIndexChange(selectedIndex: Int) {
        _selectedCardIndex.value = selectedIndex
    }

    private suspend fun mapEntityToUiModel(entity: ManualBuyEducationData): ManualBuyEducationDataUiModel {
        return ManualBuyEducationDataUiModel(
            toolBarText = entity.toolBarText,
            introTitle = entity.introTitle,
            introSubtitle = entity.introSubtitle,
            educationCardList = educationCardEntitiesToUiModel(entity.educationCardList),
            saveButtonCta = entity.saveButtonCta,
            ctaLottie = entity.ctaLottie,
            screenType = entity.screenType,
            cohort = entity.cohort,
            combination = entity.combination,
            collapseCardTiltInterval = entity.collapseCardTiltInterval,
            collapseExpandIntroInterval = entity.collapseExpandIntroInterval,
            bottomToCenterTranslationInterval = entity.bottomToCenterTranslationInterval,
            expandCardStayInterval = entity.expandCardStayInterval,
            seenCount = entity.seenCount,
            actionText = entity.actionText,
            shouldShowOnLandingPage = entity.shouldShowOnLandingPage,
            toolBarIcon = getImageBitMap(entity.toolBarIcon),
            introSubtitleIcon = getImageBitMap(entity.introSubtitleIcon),
            shouldShowBeforeNavigating = entity.shouldShowBeforeNavigating
        )
    }

    private suspend fun educationCardEntitiesToUiModel(entities: List<EducationCard>): List<EducationCardUiModel> {
        val result = mutableListOf<EducationCardUiModel>()
        entities.forEach { entity ->
            result.add(
                EducationCardUiModel(
                    image = getImageBitMap(entity.image),
                    collapsedStateText = entity.collapsedStateText,
                    expandStateText = entity.expandStateText,
                    backGroundColor = entity.backGroundColor,
                    strokeStartColor = entity.strokeStartColor,
                    strokeEndColor = entity.strokeEndColor,
                    startGradient = entity.startGradient,
                    endGradient = entity.endGradient
                )
            )
        }

        return result
    }

    private suspend fun getImageBitMap(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                withTimeoutOrNull(10_000) {
                    Glide.with(MainApplication.getAppContext())
                        .asBitmap()
                        .load(imageUrl)
                        .submit()
                        .get()

                }
            } catch (e: Exception) {
                _toastMessage.send("Something Went Wrong")
                null
            }
        }
    }
}