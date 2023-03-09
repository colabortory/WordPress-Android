package org.wordpress.android.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class LocalFeatureFlagDefault(val defaultValue: Boolean = false)
