package ep.rest

import java.io.Serializable

data class Item(
        val ID_ARTIKEL: Int = 0,
        val PATH_TO_IMG: String = "",
        val KATEGORIJA: Int = 0,
        val NAZIV_ARTIKEL: String = "",
        val OPIS: String = "",
        val CENA: Double = 0.0) : Serializable
