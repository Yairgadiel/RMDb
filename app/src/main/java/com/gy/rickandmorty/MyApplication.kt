package com.gy.rickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * extending android application class in order to use Hilt dependency injection
 */
@HiltAndroidApp
class MyApplication : Application()