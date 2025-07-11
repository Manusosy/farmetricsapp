package org.farmetricsapp.domain.model

data class Region(
    val id: String,
    val name: String,
    val districts: List<District>
)

data class District(
    val id: String,
    val name: String,
    val locations: List<Location>
)

data class Location(
    val id: String,
    val name: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)

// Ghana's regions with their districts and locations
object GhanaLocations {
    val regions = listOf(
        Region(
            id = "R001",
            name = "Greater Accra",
            districts = listOf(
                District(
                    id = "D001",
                    name = "Accra Metropolitan",
                    locations = listOf(
                        Location(id = "L001", name = "Adabraka"),
                        Location(id = "L002", name = "Osu"),
                        Location(id = "L003", name = "Jamestown")
                    )
                ),
                District(
                    id = "D002",
                    name = "Tema Metropolitan",
                    locations = listOf(
                        Location(id = "L004", name = "Tema Central"),
                        Location(id = "L005", name = "Tema East"),
                        Location(id = "L006", name = "Tema West")
                    )
                )
            )
        ),
        Region(
            id = "R002",
            name = "Ashanti",
            districts = listOf(
                District(
                    id = "D003",
                    name = "Kumasi Metropolitan",
                    locations = listOf(
                        Location(id = "L007", name = "Adum"),
                        Location(id = "L008", name = "Asokwa"),
                        Location(id = "L009", name = "Bantama")
                    )
                ),
                District(
                    id = "D004",
                    name = "Obuasi Municipal",
                    locations = listOf(
                        Location(id = "L010", name = "Obuasi Central"),
                        Location(id = "L011", name = "Kunka"),
                        Location(id = "L012", name = "Tutuka")
                    )
                )
            )
        ),
        Region(
            id = "R003",
            name = "Northern",
            districts = listOf(
                District(
                    id = "D005",
                    name = "Tamale Metropolitan",
                    locations = listOf(
                        Location(id = "L013", name = "Tamale Central"),
                        Location(id = "L014", name = "Sagnarigu"),
                        Location(id = "L015", name = "Choggu")
                    )
                )
            )
        ),
        Region(
            id = "R004",
            name = "Western",
            districts = listOf(
                District(
                    id = "D006",
                    name = "Sekondi-Takoradi Metropolitan",
                    locations = listOf(
                        Location(id = "L016", name = "Sekondi"),
                        Location(id = "L017", name = "Takoradi"),
                        Location(id = "L018", name = "Kwesimintsim")
                    )
                )
            )
        ),
        Region(
            id = "R005",
            name = "Volta",
            districts = listOf(
                District(
                    id = "D007",
                    name = "Ho Municipal",
                    locations = listOf(
                        Location(id = "L019", name = "Ho Central"),
                        Location(id = "L020", name = "Dome"),
                        Location(id = "L021", name = "Hliha")
                    )
                )
            )
        )
        // Add more regions as needed
    )
} 