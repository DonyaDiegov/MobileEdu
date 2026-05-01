package com.example.mobileedu.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowDpSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.mobileedu.R
import com.example.mobileedu.adaptiveHorizontalPadding
import com.example.mobileedu.component.HomeComponent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomeScreen(component: HomeComponent) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.home_screen_topbar_title))
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
            var text by remember { mutableStateOf("") }
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text(stringResource(R.string.home_screen_go_to_second_screen_text_field_label)) }
            )
            Button(onClick = {
                component.navigateToSecondScreen(text)
            }) {
                Text(stringResource(R.string.home_screen_go_to_second_screen_button))
            }
        }
    }
}