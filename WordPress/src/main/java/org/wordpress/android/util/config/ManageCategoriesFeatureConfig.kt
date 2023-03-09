package org.wordpress.android.util.config

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import javax.inject.Inject

/**
 * Configuration of the manage categories feature
 */
@RemoteFeatureFlagDefault(ManageCategoriesFeatureConfig.MANAGE_CATEGORIES_REMOTE_FIELD, true)
class ManageCategoriesFeatureConfig
@Inject constructor(
    appConfig: AppConfig
) : FeatureConfig(
    appConfig,
    BuildConfig.MANAGE_CATEGORIES,
    MANAGE_CATEGORIES_REMOTE_FIELD
) {
    companion object {
        const val MANAGE_CATEGORIES_REMOTE_FIELD = "manage_categories"
    }
}
