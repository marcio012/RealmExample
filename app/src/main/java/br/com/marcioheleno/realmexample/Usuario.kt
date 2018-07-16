package br.com.marcioheleno.realmexample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Usuario: RealmObject() {

    @PrimaryKey var id: Long = 0
    var nome: String? = null
    var email: String? = null



}