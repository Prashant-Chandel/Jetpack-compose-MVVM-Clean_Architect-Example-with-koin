package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final ActivityLibraryAccessors laccForActivityLibraryAccessors = new ActivityLibraryAccessors(owner);
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final ConverterLibraryAccessors laccForConverterLibraryAccessors = new ConverterLibraryAccessors(owner);
    private final EncryptedLibraryAccessors laccForEncryptedLibraryAccessors = new EncryptedLibraryAccessors(owner);
    private final GoogleLibraryAccessors laccForGoogleLibraryAccessors = new GoogleLibraryAccessors(owner);
    private final KoinLibraryAccessors laccForKoinLibraryAccessors = new KoinLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final LandscapistLibraryAccessors laccForLandscapistLibraryAccessors = new LandscapistLibraryAccessors(owner);
    private final LoggingLibraryAccessors laccForLoggingLibraryAccessors = new LoggingLibraryAccessors(owner);
    private final LottieLibraryAccessors laccForLottieLibraryAccessors = new LottieLibraryAccessors(owner);
    private final MockitoLibraryAccessors laccForMockitoLibraryAccessors = new MockitoLibraryAccessors(owner);
    private final ShimmerLibraryAccessors laccForShimmerLibraryAccessors = new ShimmerLibraryAccessors(owner);
    private final TestLibraryAccessors laccForTestLibraryAccessors = new TestLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for okhttp (com.squareup.okhttp3:okhttp)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getOkhttp() { return create("okhttp"); }

        /**
         * Creates a dependency provider for retrofit (com.squareup.retrofit2:retrofit)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getRetrofit() { return create("retrofit"); }

    /**
     * Returns the group of libraries at activity
     */
    public ActivityLibraryAccessors getActivity() { return laccForActivityLibraryAccessors; }

    /**
     * Returns the group of libraries at androidx
     */
    public AndroidxLibraryAccessors getAndroidx() { return laccForAndroidxLibraryAccessors; }

    /**
     * Returns the group of libraries at converter
     */
    public ConverterLibraryAccessors getConverter() { return laccForConverterLibraryAccessors; }

    /**
     * Returns the group of libraries at encrypted
     */
    public EncryptedLibraryAccessors getEncrypted() { return laccForEncryptedLibraryAccessors; }

    /**
     * Returns the group of libraries at google
     */
    public GoogleLibraryAccessors getGoogle() { return laccForGoogleLibraryAccessors; }

    /**
     * Returns the group of libraries at koin
     */
    public KoinLibraryAccessors getKoin() { return laccForKoinLibraryAccessors; }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() { return laccForKotlinxLibraryAccessors; }

    /**
     * Returns the group of libraries at landscapist
     */
    public LandscapistLibraryAccessors getLandscapist() { return laccForLandscapistLibraryAccessors; }

    /**
     * Returns the group of libraries at logging
     */
    public LoggingLibraryAccessors getLogging() { return laccForLoggingLibraryAccessors; }

    /**
     * Returns the group of libraries at lottie
     */
    public LottieLibraryAccessors getLottie() { return laccForLottieLibraryAccessors; }

    /**
     * Returns the group of libraries at mockito
     */
    public MockitoLibraryAccessors getMockito() { return laccForMockitoLibraryAccessors; }

    /**
     * Returns the group of libraries at shimmer
     */
    public ShimmerLibraryAccessors getShimmer() { return laccForShimmerLibraryAccessors; }

    /**
     * Returns the group of libraries at test
     */
    public TestLibraryAccessors getTest() { return laccForTestLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class ActivityLibraryAccessors extends SubDependencyFactory {

        public ActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.activity:activity-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("activity.compose"); }

    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxActivityLibraryAccessors laccForAndroidxActivityLibraryAccessors = new AndroidxActivityLibraryAccessors(owner);
        private final AndroidxComposeLibraryAccessors laccForAndroidxComposeLibraryAccessors = new AndroidxComposeLibraryAccessors(owner);
        private final AndroidxConstraintlayoutLibraryAccessors laccForAndroidxConstraintlayoutLibraryAccessors = new AndroidxConstraintlayoutLibraryAccessors(owner);
        private final AndroidxCoreLibraryAccessors laccForAndroidxCoreLibraryAccessors = new AndroidxCoreLibraryAccessors(owner);
        private final AndroidxLifecycleLibraryAccessors laccForAndroidxLifecycleLibraryAccessors = new AndroidxLifecycleLibraryAccessors(owner);
        private final AndroidxNavigationLibraryAccessors laccForAndroidxNavigationLibraryAccessors = new AndroidxNavigationLibraryAccessors(owner);
        private final AndroidxRoomLibraryAccessors laccForAndroidxRoomLibraryAccessors = new AndroidxRoomLibraryAccessors(owner);
        private final AndroidxTestLibraryAccessors laccForAndroidxTestLibraryAccessors = new AndroidxTestLibraryAccessors(owner);
        private final AndroidxUiLibraryAccessors laccForAndroidxUiLibraryAccessors = new AndroidxUiLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.activity
         */
        public AndroidxActivityLibraryAccessors getActivity() { return laccForAndroidxActivityLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.compose
         */
        public AndroidxComposeLibraryAccessors getCompose() { return laccForAndroidxComposeLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.constraintlayout
         */
        public AndroidxConstraintlayoutLibraryAccessors getConstraintlayout() { return laccForAndroidxConstraintlayoutLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.core
         */
        public AndroidxCoreLibraryAccessors getCore() { return laccForAndroidxCoreLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.lifecycle
         */
        public AndroidxLifecycleLibraryAccessors getLifecycle() { return laccForAndroidxLifecycleLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.navigation
         */
        public AndroidxNavigationLibraryAccessors getNavigation() { return laccForAndroidxNavigationLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.room
         */
        public AndroidxRoomLibraryAccessors getRoom() { return laccForAndroidxRoomLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.test
         */
        public AndroidxTestLibraryAccessors getTest() { return laccForAndroidxTestLibraryAccessors; }

        /**
         * Returns the group of libraries at androidx.ui
         */
        public AndroidxUiLibraryAccessors getUi() { return laccForAndroidxUiLibraryAccessors; }

    }

    public static class AndroidxActivityLibraryAccessors extends SubDependencyFactory {

        public AndroidxActivityLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.activity:activity-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.activity.ktx"); }

    }

    public static class AndroidxComposeLibraryAccessors extends SubDependencyFactory {
        private final AndroidxComposeUiLibraryAccessors laccForAndroidxComposeUiLibraryAccessors = new AndroidxComposeUiLibraryAccessors(owner);

        public AndroidxComposeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for bom (androidx.compose:compose-bom)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBom() { return create("androidx.compose.bom"); }

            /**
             * Creates a dependency provider for material (androidx.compose.material:material)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial() { return create("androidx.compose.material"); }

            /**
             * Creates a dependency provider for material3 (androidx.compose.material3:material3)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial3() { return create("androidx.compose.material3"); }

        /**
         * Returns the group of libraries at androidx.compose.ui
         */
        public AndroidxComposeUiLibraryAccessors getUi() { return laccForAndroidxComposeUiLibraryAccessors; }

    }

    public static class AndroidxComposeUiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxComposeUiToolingLibraryAccessors laccForAndroidxComposeUiToolingLibraryAccessors = new AndroidxComposeUiToolingLibraryAccessors(owner);

        public AndroidxComposeUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ui (androidx.compose.ui:ui)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.ui"); }

            /**
             * Creates a dependency provider for android (androidx.compose.ui:ui-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("androidx.compose.ui.android"); }

        /**
         * Returns the group of libraries at androidx.compose.ui.tooling
         */
        public AndroidxComposeUiToolingLibraryAccessors getTooling() { return laccForAndroidxComposeUiToolingLibraryAccessors; }

    }

    public static class AndroidxComposeUiToolingLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxComposeUiToolingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for tooling (androidx.compose.ui:ui-tooling)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.compose.ui.tooling"); }

            /**
             * Creates a dependency provider for preview (androidx.compose.ui:ui-tooling-preview)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreview() { return create("androidx.compose.ui.tooling.preview"); }

    }

    public static class AndroidxConstraintlayoutLibraryAccessors extends SubDependencyFactory {

        public AndroidxConstraintlayoutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.constraintlayout:constraintlayout-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.constraintlayout.compose"); }

    }

    public static class AndroidxCoreLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxCoreLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (androidx.test:core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.core"); }

            /**
             * Creates a dependency provider for ktx (androidx.core:core-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.core.ktx"); }

            /**
             * Creates a dependency provider for testing (androidx.arch.core:core-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("androidx.core.testing"); }

    }

    public static class AndroidxLifecycleLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleRuntimeLibraryAccessors laccForAndroidxLifecycleRuntimeLibraryAccessors = new AndroidxLifecycleRuntimeLibraryAccessors(owner);

        public AndroidxLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at androidx.lifecycle.runtime
         */
        public AndroidxLifecycleRuntimeLibraryAccessors getRuntime() { return laccForAndroidxLifecycleRuntimeLibraryAccessors; }

    }

    public static class AndroidxLifecycleRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.lifecycle:lifecycle-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.lifecycle.runtime.ktx"); }

    }

    public static class AndroidxNavigationLibraryAccessors extends SubDependencyFactory {
        private final AndroidxNavigationRuntimeLibraryAccessors laccForAndroidxNavigationRuntimeLibraryAccessors = new AndroidxNavigationRuntimeLibraryAccessors(owner);

        public AndroidxNavigationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (androidx.navigation:navigation-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("androidx.navigation.compose"); }

        /**
         * Returns the group of libraries at androidx.navigation.runtime
         */
        public AndroidxNavigationRuntimeLibraryAccessors getRuntime() { return laccForAndroidxNavigationRuntimeLibraryAccessors; }

    }

    public static class AndroidxNavigationRuntimeLibraryAccessors extends SubDependencyFactory {

        public AndroidxNavigationRuntimeLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ktx (androidx.navigation:navigation-runtime-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.navigation.runtime.ktx"); }

    }

    public static class AndroidxRoomLibraryAccessors extends SubDependencyFactory {

        public AndroidxRoomLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.room:room-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() { return create("androidx.room.compiler"); }

            /**
             * Creates a dependency provider for ktx (androidx.room:room-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() { return create("androidx.room.ktx"); }

            /**
             * Creates a dependency provider for runtime (androidx.room:room-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() { return create("androidx.room.runtime"); }

            /**
             * Creates a dependency provider for testing (androidx.room:room-testing)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTesting() { return create("androidx.room.testing"); }

    }

    public static class AndroidxTestLibraryAccessors extends SubDependencyFactory {
        private final AndroidxTestExtLibraryAccessors laccForAndroidxTestExtLibraryAccessors = new AndroidxTestExtLibraryAccessors(owner);

        public AndroidxTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for orchestrator (androidx.test:orchestrator)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getOrchestrator() { return create("androidx.test.orchestrator"); }

            /**
             * Creates a dependency provider for rules (androidx.test:rules)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRules() { return create("androidx.test.rules"); }

            /**
             * Creates a dependency provider for runner (androidx.test:runner)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRunner() { return create("androidx.test.runner"); }

            /**
             * Creates a dependency provider for truth (androidx.test.ext:truth)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTruth() { return create("androidx.test.truth"); }

        /**
         * Returns the group of libraries at androidx.test.ext
         */
        public AndroidxTestExtLibraryAccessors getExt() { return laccForAndroidxTestExtLibraryAccessors; }

    }

    public static class AndroidxTestExtLibraryAccessors extends SubDependencyFactory {

        public AndroidxTestExtLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit (androidx.test.ext:junit)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit() { return create("androidx.test.ext.junit"); }

    }

    public static class AndroidxUiLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxUiTestLibraryAccessors laccForAndroidxUiTestLibraryAccessors = new AndroidxUiTestLibraryAccessors(owner);

        public AndroidxUiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for ui (androidx.compose.ui:ui)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.ui"); }

        /**
         * Returns the group of libraries at androidx.ui.test
         */
        public AndroidxUiTestLibraryAccessors getTest() { return laccForAndroidxUiTestLibraryAccessors; }

    }

    public static class AndroidxUiTestLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {
        private final AndroidxUiTestJunit4LibraryAccessors laccForAndroidxUiTestJunit4LibraryAccessors = new AndroidxUiTestJunit4LibraryAccessors(owner);

        public AndroidxUiTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (androidx.compose.ui:ui-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.ui.test"); }

            /**
             * Creates a dependency provider for manifest (androidx.compose.ui:ui-test-manifest)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getManifest() { return create("androidx.ui.test.manifest"); }

        /**
         * Returns the group of libraries at androidx.ui.test.junit4
         */
        public AndroidxUiTestJunit4LibraryAccessors getJunit4() { return laccForAndroidxUiTestJunit4LibraryAccessors; }

    }

    public static class AndroidxUiTestJunit4LibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public AndroidxUiTestJunit4LibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for junit4 (androidx.compose.ui:ui-test-junit4)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("androidx.ui.test.junit4"); }

            /**
             * Creates a dependency provider for android (androidx.compose.ui:ui-test-junit4-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAndroid() { return create("androidx.ui.test.junit4.android"); }

    }

    public static class ConverterLibraryAccessors extends SubDependencyFactory {

        public ConverterLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for gson (com.squareup.retrofit2:converter-gson)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGson() { return create("converter.gson"); }

            /**
             * Creates a dependency provider for scalars (com.squareup.retrofit2:converter-scalars)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getScalars() { return create("converter.scalars"); }

    }

    public static class EncryptedLibraryAccessors extends SubDependencyFactory {

        public EncryptedLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for preferences (com.pddstudio:encrypted-preferences)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getPreferences() { return create("encrypted.preferences"); }

    }

    public static class GoogleLibraryAccessors extends SubDependencyFactory {
        private final GoogleAndroidLibraryAccessors laccForGoogleAndroidLibraryAccessors = new GoogleAndroidLibraryAccessors(owner);
        private final GoogleTestLibraryAccessors laccForGoogleTestLibraryAccessors = new GoogleTestLibraryAccessors(owner);

        public GoogleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at google.android
         */
        public GoogleAndroidLibraryAccessors getAndroid() { return laccForGoogleAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at google.test
         */
        public GoogleTestLibraryAccessors getTest() { return laccForGoogleTestLibraryAccessors; }

    }

    public static class GoogleAndroidLibraryAccessors extends SubDependencyFactory {

        public GoogleAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for material (com.google.android.material:material)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getMaterial() { return create("google.android.material"); }

    }

    public static class GoogleTestLibraryAccessors extends SubDependencyFactory {

        public GoogleTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for truth (com.google.truth:truth)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTruth() { return create("google.test.truth"); }

    }

    public static class KoinLibraryAccessors extends SubDependencyFactory {
        private final KoinAndroidLibraryAccessors laccForKoinAndroidLibraryAccessors = new KoinAndroidLibraryAccessors(owner);
        private final KoinTestLibraryAccessors laccForKoinTestLibraryAccessors = new KoinTestLibraryAccessors(owner);

        public KoinLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (io.insert-koin:koin-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("koin.core"); }

        /**
         * Returns the group of libraries at koin.android
         */
        public KoinAndroidLibraryAccessors getAndroid() { return laccForKoinAndroidLibraryAccessors; }

        /**
         * Returns the group of libraries at koin.test
         */
        public KoinTestLibraryAccessors getTest() { return laccForKoinTestLibraryAccessors; }

    }

    public static class KoinAndroidLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public KoinAndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for android (io.insert-koin:koin-android)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("koin.android"); }

            /**
             * Creates a dependency provider for compat (io.insert-koin:koin-android-compat)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompat() { return create("koin.android.compat"); }

    }

    public static class KoinTestLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public KoinTestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (io.insert-koin:koin-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("koin.test"); }

            /**
             * Creates a dependency provider for junit4 (io.insert-koin:koin-test-junit4)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJunit4() { return create("koin.test.junit4"); }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxCoroutinesLibraryAccessors laccForKotlinxCoroutinesLibraryAccessors = new KotlinxCoroutinesLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at kotlinx.coroutines
         */
        public KotlinxCoroutinesLibraryAccessors getCoroutines() { return laccForKotlinxCoroutinesLibraryAccessors; }

    }

    public static class KotlinxCoroutinesLibraryAccessors extends SubDependencyFactory {

        public KotlinxCoroutinesLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for test (org.jetbrains.kotlinx:kotlinx-coroutines-test)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getTest() { return create("kotlinx.coroutines.test"); }

    }

    public static class LandscapistLibraryAccessors extends SubDependencyFactory {

        public LandscapistLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for glide (com.github.skydoves:landscapist-glide)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getGlide() { return create("landscapist.glide"); }

    }

    public static class LoggingLibraryAccessors extends SubDependencyFactory {

        public LoggingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for interceptor (com.squareup.okhttp3:logging-interceptor)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getInterceptor() { return create("logging.interceptor"); }

    }

    public static class LottieLibraryAccessors extends SubDependencyFactory {

        public LottieLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (com.airbnb.android:lottie-compose)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("lottie.compose"); }

    }

    public static class MockitoLibraryAccessors extends SubDependencyFactory {

        public MockitoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for core (org.mockito:mockito-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("mockito.core"); }

            /**
             * Creates a dependency provider for inline (org.mockito:mockito-inline)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getInline() { return create("mockito.inline"); }

            /**
             * Creates a dependency provider for kotlin (com.nhaarman.mockitokotlin2:mockito-kotlin)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKotlin() { return create("mockito.kotlin"); }

    }

    public static class ShimmerLibraryAccessors extends SubDependencyFactory {

        public ShimmerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compose (com.valentinilk.shimmer:compose-shimmer)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompose() { return create("shimmer.compose"); }

    }

    public static class TestLibraryAccessors extends SubDependencyFactory {
        private final TestEspressoLibraryAccessors laccForTestEspressoLibraryAccessors = new TestEspressoLibraryAccessors(owner);
        private final TestIdlingLibraryAccessors laccForTestIdlingLibraryAccessors = new TestIdlingLibraryAccessors(owner);

        public TestLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at test.espresso
         */
        public TestEspressoLibraryAccessors getEspresso() { return laccForTestEspressoLibraryAccessors; }

        /**
         * Returns the group of libraries at test.idling
         */
        public TestIdlingLibraryAccessors getIdling() { return laccForTestIdlingLibraryAccessors; }

    }

    public static class TestEspressoLibraryAccessors extends SubDependencyFactory {

        public TestEspressoLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for accessibility (androidx.test.espresso:espresso-accessibility)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getAccessibility() { return create("test.espresso.accessibility"); }

            /**
             * Creates a dependency provider for contrib (androidx.test.espresso:espresso-contrib)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getContrib() { return create("test.espresso.contrib"); }

            /**
             * Creates a dependency provider for core (androidx.test.espresso:espresso-core)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("test.espresso.core"); }

            /**
             * Creates a dependency provider for intents (androidx.test.espresso:espresso-intents)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getIntents() { return create("test.espresso.intents"); }

    }

    public static class TestIdlingLibraryAccessors extends SubDependencyFactory {

        public TestIdlingLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for concurrent (androidx.test.espresso.idling:idling-concurrent)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getConcurrent() { return create("test.idling.concurrent"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidActivityKtx (1.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidActivityKtx() { return getVersion("androidActivityKtx"); }

            /**
             * Returns the version associated to this alias: androidConstraintLayoutCompose (1.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidConstraintLayoutCompose() { return getVersion("androidConstraintLayoutCompose"); }

            /**
             * Returns the version associated to this alias: androidMaterial (1.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidMaterial() { return getVersion("androidMaterial"); }

            /**
             * Returns the version associated to this alias: androidxActivityCompose (1.8.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxActivityCompose() { return getVersion("androidxActivityCompose"); }

            /**
             * Returns the version associated to this alias: androidxComposeBom (2023.10.01)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxComposeBom() { return getVersion("androidxComposeBom"); }

            /**
             * Returns the version associated to this alias: androidxCoreKtx (1.12.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxCoreKtx() { return getVersion("androidxCoreKtx"); }

            /**
             * Returns the version associated to this alias: androidxLifecycleKtx (2.6.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxLifecycleKtx() { return getVersion("androidxLifecycleKtx"); }

            /**
             * Returns the version associated to this alias: androidxTestCore (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAndroidxTestCore() { return getVersion("androidxTestCore"); }

            /**
             * Returns the version associated to this alias: composeVersion (1.5.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getComposeVersion() { return getVersion("composeVersion"); }

            /**
             * Returns the version associated to this alias: constraintlayout (2.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("constraintlayout"); }

            /**
             * Returns the version associated to this alias: coreTesting (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCoreTesting() { return getVersion("coreTesting"); }

            /**
             * Returns the version associated to this alias: encryptedPreferences (1.3.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEncryptedPreferences() { return getVersion("encryptedPreferences"); }

            /**
             * Returns the version associated to this alias: espressoVersion (3.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEspressoVersion() { return getVersion("espressoVersion"); }

            /**
             * Returns the version associated to this alias: googleTestTruth (1.1.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getGoogleTestTruth() { return getVersion("googleTestTruth"); }

            /**
             * Returns the version associated to this alias: junit (4.13.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJunit() { return getVersion("junit"); }

            /**
             * Returns the version associated to this alias: koinVersion (3.4.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKoinVersion() { return getVersion("koinVersion"); }

            /**
             * Returns the version associated to this alias: kotlinxCoroutinesTest (1.7.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxCoroutinesTest() { return getVersion("kotlinxCoroutinesTest"); }

            /**
             * Returns the version associated to this alias: landscapistGlide (1.3.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLandscapistGlide() { return getVersion("landscapistGlide"); }

            /**
             * Returns the version associated to this alias: loggingInterceptor (4.10.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLoggingInterceptor() { return getVersion("loggingInterceptor"); }

            /**
             * Returns the version associated to this alias: lottie (4.2.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLottie() { return getVersion("lottie"); }

            /**
             * Returns the version associated to this alias: mockitoCore (5.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockitoCore() { return getVersion("mockitoCore"); }

            /**
             * Returns the version associated to this alias: mockitoInline (2.13.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockitoInline() { return getVersion("mockitoInline"); }

            /**
             * Returns the version associated to this alias: mockitoKotlin (2.2.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMockitoKotlin() { return getVersion("mockitoKotlin"); }

            /**
             * Returns the version associated to this alias: navVersion (2.7.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavVersion() { return getVersion("navVersion"); }

            /**
             * Returns the version associated to this alias: navigationRuntimeKtx (2.7.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getNavigationRuntimeKtx() { return getVersion("navigationRuntimeKtx"); }

            /**
             * Returns the version associated to this alias: okhttp (4.11.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getOkhttp() { return getVersion("okhttp"); }

            /**
             * Returns the version associated to this alias: retrofitVersion (2.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRetrofitVersion() { return getVersion("retrofitVersion"); }

            /**
             * Returns the version associated to this alias: roomKtx (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRoomKtx() { return getVersion("roomKtx"); }

            /**
             * Returns the version associated to this alias: roomVersion (2.6.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRoomVersion() { return getVersion("roomVersion"); }

            /**
             * Returns the version associated to this alias: shimmer (1.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getShimmer() { return getVersion("shimmer"); }

            /**
             * Returns the version associated to this alias: testJunitExt (1.1.5)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestJunitExt() { return getVersion("testJunitExt"); }

            /**
             * Returns the version associated to this alias: testOrchestrator (1.4.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestOrchestrator() { return getVersion("testOrchestrator"); }

            /**
             * Returns the version associated to this alias: testRules (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestRules() { return getVersion("testRules"); }

            /**
             * Returns the version associated to this alias: testRunner (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestRunner() { return getVersion("testRunner"); }

            /**
             * Returns the version associated to this alias: testTruth (1.5.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTestTruth() { return getVersion("testTruth"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

    }

}
