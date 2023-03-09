package org.wordpress.android.annotation

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class RemoteFeatureFlagDefault(val remoteField: String, val defaultValue: Boolean = false)
