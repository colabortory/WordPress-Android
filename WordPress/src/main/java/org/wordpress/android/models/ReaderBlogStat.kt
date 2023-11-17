package org.wordpress.android.models

data class ReaderBlogStat(
    val blogId: Long,
    val blogName: String?,
    val blogUrl: String?,
    val blogImageUrl: String?,
    val authorAvatar: String?,
    val readTime: Long,
)
