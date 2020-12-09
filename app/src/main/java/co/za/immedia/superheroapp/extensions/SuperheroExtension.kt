package co.za.immedia.superheroapp.extensions

import co.za.immedia.superheroapp.database.tables.SuperheroesTable
import co.za.immedia.models.Image
import co.za.immedia.models.Superhero

fun Superhero.toSuperheroesTable() : SuperheroesTable {
    return SuperheroesTable(this.id, this.name, this.image?.url)
}

fun SuperheroesTable.toSuperhero() : Superhero {
    val image = Image(this.imageUrl)
    return Superhero(this.id, this.name, null, null, null, null, null, image)
}