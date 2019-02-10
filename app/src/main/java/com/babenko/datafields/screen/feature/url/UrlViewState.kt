package com.babenko.datafields.screen.feature.url

sealed class UrlViewState {
    object LoadingStarted : UrlViewState()
    class Loaded : UrlViewState()
    class Error(val throwable: Throwable) : UrlViewState()
}