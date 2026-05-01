package com.example.mobileedu.component

import com.arkivanov.decompose.ComponentContext

interface HomeComponent {
    fun navigateToSecondScreen(param: String)
}

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val onNavigateToSecondScreen: (String) -> Unit
) : HomeComponent, ComponentContext by componentContext {

    override fun navigateToSecondScreen(param: String) {
        onNavigateToSecondScreen(param)
    }
}