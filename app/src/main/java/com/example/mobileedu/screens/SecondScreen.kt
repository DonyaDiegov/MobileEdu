package com.example.mobileedu.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowDpSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mobileedu.R
import com.example.mobileedu.adaptiveHorizontalPadding
import com.example.mobileedu.component.SecondComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SecondScreen(component: SecondComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.second_screen_topbar_title))
                },
            )
        },
    ) { contentPadding ->
        Column(
            Modifier.padding(contentPadding).adaptiveHorizontalPadding(
                currentWindowDpSize(),
                currentWindowAdaptiveInfo().windowSizeClass
            )
        ) {
            SelectionContainer {
                Text(component.param)
            }
        }
    }
}