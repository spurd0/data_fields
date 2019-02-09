package com.babenko.datafields.application.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(
    @get:Singleton
    @get:Provides
    internal val context: Context
)
