package com.rick.groovy.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {

    @get:Rule
    var coroutineTestRule = MainCoroutineScopeRule()

    // allows to have instant livedata results
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

}