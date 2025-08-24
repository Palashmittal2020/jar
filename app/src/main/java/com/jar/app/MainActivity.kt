package com.jar.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.jar.app.presentation.onBoarding.OnBoardingScreen
import com.jar.app.presentation.onBoarding.OnBoardingScreenViewModel
import com.jar.app.ui.theme.JarTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent

class MainActivity : ComponentActivity(), KoinComponent {
    val viewModel: OnBoardingScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setObservers()
        setContent {
            JarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        OnBoardingScreen(
                            onBoardingDataFlow = viewModel.onBoardingData,
                            selectedCardIndexFLow = viewModel.selectedCardIndex,
                            onSelectedCardIndexChange = viewModel::onSelectedCardIndexChange
                        )
                    }
                }
            }
        }
    }

    private fun setObservers() {

        lifecycleScope.launchWhenStarted {
            viewModel.toastMessage.collectLatest {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
