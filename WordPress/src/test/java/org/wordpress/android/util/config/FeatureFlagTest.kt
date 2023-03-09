package org.wordpress.android.util.config

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FeatureFlagTest {
    @Mock
    lateinit var appConfig: AppConfig
    private lateinit var featureFlag: FeatureFlag

    @Before
    fun setUp() {
        featureFlag = TestFeatureFlag(appConfig)
    }

    @Test
    fun `returns isEnabled == true from app config`() {
        whenever(appConfig.isEnabled(featureFlag)).thenReturn(true)

        assertThat(featureFlag.isEnabled()).isTrue()
    }

    @Test
    fun `returns isEnabled == false from app config`() {
        whenever(appConfig.isEnabled(featureFlag)).thenReturn(false)

        assertThat(featureFlag.isEnabled()).isFalse()
    }

    private class TestFeatureFlag(appConfig: AppConfig) : FeatureFlag(appConfig, true, "remote_field")
}
