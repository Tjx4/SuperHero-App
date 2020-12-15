package co.za.immedia.commons.extensions

import co.za.immedia.commons.models.Biography
import co.za.immedia.commons.models.Image
import co.za.immedia.commons.models.PowerStats
import co.za.immedia.commons.models.Superhero
import co.za.immedia.persistence.room.tables.SuperheroesTable

fun Superhero.toSuperheroesTable() : SuperheroesTable {
    return SuperheroesTable(this.id, this.name, this.powerstats?.intelligence, this.powerstats?.strength, this.powerstats?.speed, this.biography?.fullName, this.biography?.alterEgos, this.biography?.placeOfBirth, this.biography?.publisher, this.image?.url)
}

fun SuperheroesTable.toSuperhero() : Superhero {
    val powerStats = PowerStats(this.intelligence, this.strength, this.speed)
    val biography = Biography(this.fullName, this.alterEgos, null, this.placeOfBirth, null, this.publisher)
    val image = Image(this.imageUrl)

    return Superhero(this.id, this.name, powerStats, biography, null, null, null, image)
}