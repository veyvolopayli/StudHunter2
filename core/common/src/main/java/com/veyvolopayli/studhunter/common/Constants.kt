package com.veyvolopayli.studhunter.common

object Constants {
    /** Must match `FileProvider` `android:authorities` in the app manifest (${applicationId}.provider). */
    const val FILE_PROVIDER_AUTHORITY = "com.veyvolopayli.studhunter.provider"

    const val BASE_URL = "http://176.123.163.100:8081/"
    const val LOCALHOST = "http://192.168.1.71:8081/"
    const val WEBSOCKET_BASE_URL = "ws://176.123.163.100:8081/"
    const val WEBSOCKET_LOCALHOST = "ws://10.8.0.6:8081/"
//    const val BASE_URL = LOCALHOST
    const val PARAM_PUBLICATION_ID = "publicationId"
    const val PARAM_PUBLICATION_CATEGORY = "publicationCategory"
    const val PARAM_PUBLICATION_QUERY = "publicationQuery"
    const val JWT = "jwt"
    const val USER_ID = "user_id"
    const val CLOUD_PUB_IMAGES_PATH = "https://storage.yandexcloud.net/stud-hunter-bucket/publications/images/"
    const val CLOUD_USER_PROFILE_IMAGES_PATH = "https://storage.yandexcloud.net/stud-hunter-bucket/users/avatars/"
    val getUserAvatarUrl : (String) -> String = { "$CLOUD_USER_PROFILE_IMAGES_PATH$it" }
}