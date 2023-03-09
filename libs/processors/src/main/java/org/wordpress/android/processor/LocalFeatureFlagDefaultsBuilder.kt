package org.wordpress.android.processor

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName

class LocalFeatureFlagDefaultsBuilder(private val featuresInDevelopment: List<String>) {
    fun getContent(): FileSpec {
        val map = List::class.asClassName()
            .parameterizedBy(String::class.asClassName())
        val stringBuilder = StringBuilder()
        featuresInDevelopment.forEachIndexed { index, className ->
            stringBuilder.append("\n")
            stringBuilder.append("\"${className.split(".").last()}\"")
            if (index < featuresInDevelopment.size - 1) {
                stringBuilder.append(",")
            }
        }
        stringBuilder.append("\n")
        val remoteConfigDefaults = TypeSpec.objectBuilder("LocalFeatureFlagDefaults")
            .addProperty(
                PropertySpec.builder("localFeatureFlags", map)
                    .initializer("listOf($stringBuilder)")
                    .build()
            )
            .build()
        return FileSpec.builder("org.wordpress.android.util.config", "LocalFeatureFlagDefaults")
            .addType(remoteConfigDefaults)
            .addComment("Automatically generated file. DO NOT MODIFY")
            .indent("    ")
            .build()
    }
}
