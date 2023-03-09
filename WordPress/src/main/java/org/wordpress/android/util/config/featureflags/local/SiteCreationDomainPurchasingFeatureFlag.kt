package org.wordpress.android.util.config.featureflags.local

import org.wordpress.android.BuildConfig
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.fluxc.model.experiments.Variation.Control
import org.wordpress.android.util.config.AppConfig
import org.wordpress.android.util.config.AppConfig.FeatureState.ManuallyOverriden
import org.wordpress.android.util.config.LocalFeatureFlag
import org.wordpress.android.util.experiments.SiteCreationDomainPurchasingExperiment
import javax.inject.Inject

@LocalFeatureFlagDefault(BuildConfig.ENABLE_SITE_CREATION_DOMAIN_PURCHASING)
class SiteCreationDomainPurchasingFeatureFlag
@Inject constructor(
    appConfig: AppConfig,
    private val experiment: SiteCreationDomainPurchasingExperiment,
) : LocalFeatureFlag(
    appConfig
) {
    override fun isEnabled(): Boolean {
        return super.isEnabled() && experiment.getVariation() != Control
    }

    fun isEnabledOrManuallyOverridden() = (featureState() as? ManuallyOverriden)?.isEnabled ?: isEnabled()

    fun isEnabledState(): Boolean {
        return featureState().isEnabled
    }
}
