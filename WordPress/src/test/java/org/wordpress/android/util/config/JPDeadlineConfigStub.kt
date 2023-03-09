package org.wordpress.android.util.config

import org.mockito.kotlin.mock
import org.wordpress.android.util.config.remotefields.JPDeadlineConfig

class JPDeadlineConfigStub(
    val appConfig: AppConfig = mock(),
    val instance: JPDeadlineConfig = JPDeadlineConfig(appConfig),
)
