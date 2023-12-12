package com.clean.mvvm.utils

import com.clean.mvvm.BuildConfig


class Constants {
    companion object {
        internal const val HOME_ROUTES: String = "home_route"
        internal const val MY_FAVOURITES_ROUTES: String = "my_favourites"
        internal const val IMAGE_ID: String = "image_iD"
        internal const val FAV_ID: String = "favourite_id"
        internal const val baseUrl: String = BuildConfig.baseUrl
        internal const val SHARED_PREFERENCES: String = BuildConfig.prefName
        internal const val PREF_PASSWORD: String = BuildConfig.prefPassword
        internal const val URL: String = "image_url"
        internal const val DATABASE_NAME: String = "lbg.db"
        internal const val SUB_ID: String = "my-user97"
    }


}
