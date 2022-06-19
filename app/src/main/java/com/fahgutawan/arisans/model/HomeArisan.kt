package com.fahgutawan.arisans.model

data class HomeArisan(
    val success:Boolean,
    val body: List<HomeArisanBody>
)

data class HomeArisanBody(
    val ID: Int,
    val NamaArisan: String,
    val NanoIdArisan: String,
    val BanyakOrang: Int,
    val Nominal: Int,
    val StatusArisanPublik: Boolean
)
