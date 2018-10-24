package com.bezzo.football2

import com.bezzo.core.util.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider : CoroutineContextProvider() {
    override val main : CoroutineContext = Unconfined
}