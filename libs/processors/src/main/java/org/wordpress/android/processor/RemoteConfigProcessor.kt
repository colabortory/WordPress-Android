package org.wordpress.android.processor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import org.wordpress.android.annotation.Experiment
import org.wordpress.android.annotation.RemoteFeatureFlagDefault
import org.wordpress.android.annotation.LocalFeatureFlagDefault
import org.wordpress.android.annotation.RemoteFieldDefaultGenerater
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic.Kind

@AutoService(Processor::class) // For registering the service
@SupportedSourceVersion(SourceVersion.RELEASE_8) // to support Java 8
@SupportedAnnotationTypes(
    "org.wordpress.android.annotation.Experiment",
    "org.wordpress.android.annotation.Feature",
    "org.wordpress.android.annotation.FeatureInDevelopment",
    "org.wordpress.android.annotation.RemoteFieldDefaultGenerater"
)
class RemoteConfigProcessor : AbstractProcessor() {
    @Suppress("DEPRECATION")
    override fun process(p0: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        val experiments = roundEnvironment?.getElementsAnnotatedWith(Experiment::class.java)?.map { element ->
            val annotation = element.getAnnotation(Experiment::class.java)
            annotation.remoteField to annotation.defaultVariant
        } ?: listOf()
        val remoteFeatureNames = mutableListOf<TypeName>()
        val remoteFeatureFlags =
            roundEnvironment?.getElementsAnnotatedWith(RemoteFeatureFlagDefault::class.java)?.map { element ->
                val annotation = element.getAnnotation(RemoteFeatureFlagDefault::class.java)
                remoteFeatureNames.add(element.asType().asTypeName())
                annotation.remoteField to annotation.defaultValue.toString()
            } ?: listOf()
        val remoteFields = roundEnvironment?.getElementsAnnotatedWith(RemoteFieldDefaultGenerater::class.java)
            ?.map { element ->
                val annotation = element.getAnnotation(RemoteFieldDefaultGenerater::class.java)
                annotation.remoteField to annotation.defaultValue
            } ?: listOf()
        val localFeatureFlags = roundEnvironment?.getElementsAnnotatedWith(LocalFeatureFlagDefault::class.java)
            ?.map { element ->
                element.asType().toString()
            } ?: listOf()
        return if (experiments.isNotEmpty() || remoteFeatureFlags.isNotEmpty()) {
            generateRemoteFieldConfigDefaults(remoteFields.toMap())
            generateRemoteFeatureConfigCheck(remoteFeatureNames)
            generateRemoteFeatureConfigDefaults((experiments + remoteFeatureFlags).toMap())
            generateLocalFeatureFlagDefaults(localFeatureFlags)
            true
        } else {
            false
        }
    }

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    private fun generateRemoteFeatureConfigDefaults(
        remoteConfigDefaults: Map<String, String>
    ) {
        try {
            val fileContent = RemoteFeatureFlagDefaultsBuilder(remoteConfigDefaults).getContent()

            val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            fileContent.writeTo(File(kaptKotlinGeneratedDir))
        } catch (e: Exception) {
            processingEnv.messager.printMessage(Kind.ERROR, "Failed to generate remote feature config defaults")
        }
    }

    @Suppress("TooGenericExceptionCaught", "SwallowedException")
    private fun generateRemoteFieldConfigDefaults(
        remoteConfigDefaults: Map<String, String>
    ) {
        try {
            val fileContent = RemoteFieldConfigDefaultsBuilder(remoteConfigDefaults).getContent()

            val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            fileContent.writeTo(File(kaptKotlinGeneratedDir))
        } catch (e: Exception) {
            processingEnv.messager.printMessage(Kind.ERROR, "Failed to generate remote feature config defaults")
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun generateRemoteFeatureConfigCheck(
        remoteFeatureNames: List<TypeName>
    ) {
        try {
            val fileContent = RemoteFeatureFlagCheckBuilder(remoteFeatureNames).getContent()

            val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            fileContent.writeTo(File(kaptKotlinGeneratedDir))
        } catch (e: Exception) {
            processingEnv.messager.printMessage(
                Kind.ERROR,
                "Failed to generate remote feature config check: $e"
            )
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun generateLocalFeatureFlagDefaults(
        remoteFeatureNames: List<String>
    ) {
        try {
            val fileContent = LocalFeatureFlagDefaultsBuilder(remoteFeatureNames).getContent()

            val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            fileContent.writeTo(File(kaptKotlinGeneratedDir))
        } catch (e: Exception) {
            processingEnv.messager.printMessage(
                Kind.ERROR,
                "Failed to generate remote config check: $e"
            )
        }
    }

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }
}
