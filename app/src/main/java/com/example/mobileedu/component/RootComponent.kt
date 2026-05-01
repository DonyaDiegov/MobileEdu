package com.example.mobileedu.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface RootComponent {
    val childStack: Value<ChildStack<Config, Child>>

    fun navigate(config: Config.MainScreen)

    sealed interface Child {
        class Home(val component: HomeComponent) : Child
        class Second(val component: SecondComponent) : Child
        class About(val component: AboutComponent) : Child
    }
}

class RootComponentImpl(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<Config, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Home,
            handleBackButton = true,
            childFactory = { config, childComponentContext ->
                when (config) {
                    Config.Home -> {
                        RootComponent.Child.Home(
                            HomeComponentImpl(
                                componentContext = childComponentContext,
                                onNavigateToSecondScreen = { param ->
                                    navigation.pushNew(Config.Second(param))
                                }
                            )
                        )
                    }

                    is Config.Second -> {
                        RootComponent.Child.Second(
                            SecondComponentImpl(
                                param = config.param,
                                componentContext = childComponentContext
                            )
                        )
                    }

                    Config.About -> {
                        RootComponent.Child.About(
                            AboutComponentImpl(childComponentContext)
                        )
                    }
                }
            }
        )

    override fun navigate(config: Config.MainScreen) {
        navigation.bringToFront(config)
    }
}

@Serializable
sealed interface Config {

    @Serializable
    data object Home : Config, MainScreen

    @Serializable
    data class Second(val param: String) : Config

    @Serializable
    data object About : Config, MainScreen

    sealed interface MainScreen : Config
}