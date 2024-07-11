package com.example.marvelapp1



data class ComicsResponse(
    val code: Int,
    val status: String,
    val data: ComicsData
)

data class ComicsData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Comic>
)

data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: Thumbnail,
    val creators: Creators,
    val textObjects: List<TextObject>
)
data class TextObject(
    val type: String,
    val language: String,
    val text: String
)

data class Thumbnail(
    val path: String,
    val extension: String
)

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<CreatorItem>,
    val returned: Int
)

data class CreatorItem(
    val resourceURI: String,
    val name: String,
    val role: String
)
