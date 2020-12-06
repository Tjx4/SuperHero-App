package co.za.immedia.superheroapp.extensions

import co.za.immedia.superheroapp.database.tables.SuperheroesTable
import co.za.immedia.superheroapp.models.Superhero


fun Superhero.toSuperheroesTable() : SuperheroesTable {
    return SuperheroesTable(this.id, this.name, this.image?.url)
}