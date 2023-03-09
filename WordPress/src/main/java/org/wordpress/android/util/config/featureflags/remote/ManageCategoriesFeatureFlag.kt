package org.wordpress.android.util.config.featureflags.remote

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.FeatureFlag
import javax.inject.Inject

/**
 * Configuration of the manage categories feature
 */
@RemoteFeatureFlagDefault(ManageCategoriesFeatureFlag.MANAGE_CATEGORIES_REMOTE_FIELD, true)
class ManageCategoriesFeatureFlag
@Inject constructor(
    appConfig: AppConfig
) : FeatureFlag(
    appConfig,
    BuildConfig.MANAGE_CATEGORIES,
    MANAGE_CATEGORIES_REMOTE_FIELD
) {
    companion object {
        const val MANAGE_CATEGORIES_REMOTE_FIELD = "manage_categories"
    }
}
