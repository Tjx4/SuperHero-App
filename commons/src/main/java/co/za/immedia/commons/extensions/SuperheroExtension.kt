package co.za.immedia.commons.extensions

import co.za.immedia.commons.models.Superhero

fun Superhero.toSuperheroesTable() : SuperheroesTable {
    return SuperheroesTable(this.id, this.name, this.image?.url)
}

fun SuperheroesTable.toSuperhero() : Superhero {
    val image = Image(this.imageUrl)
    return Superhero(this.id, this.name, null, null, null, null, null, image)
}