package br.com.marcioheleno.realmexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // inicializar
        Realm.init(this)

        realmWrite()
        realmRead()
        realWhereAvancado()

    }

    private fun realmWrite() {

        val breno = Usuario()
        breno.id = 1
        breno.nome = "ioS << Android"
        breno.email = null

        val ney = Usuario()
        ney.id = 2
        ney.nome = null
        ney.email = "ney@email.com"

        val ricardo = Usuario()
        ricardo.id = 3
        ricardo.nome = "Ricardo Lima"
        ney.email = "ricardo@unifor.com"

        val realm = Realm.getDefaultInstance()

        // abre a transação
//        realm.beginTransaction()
//        realm.copyToRealm(breno)

        // grava a transação
//        realm.commitTransaction()

        // tratamento java
        /*
        try {
            realm.beginTransaction()
            realm.copyToRealm(breno)
            realm.copyToRealm(ney)
            // grava a transação
            realm.commitTransaction()
        } catch (e: Exception) {
            realm.cancelTransaction()
        } finally {
            realm.close()
        }
        */

        //kotlin
        realm.use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(breno)
            it.copyToRealmOrUpdate(ney)
            it.copyToRealmOrUpdate(ricardo)
            it.commitTransaction()
//            it.copyToRealm(breno)
        }

//        realm.copyToRealm(ney)

    }

    private fun realmRead() {
        val realm = Realm.getDefaultInstance()

        val todoUsuarsio = realm.where(Usuario::class.java).findAll()

        todoUsuarsio.forEach { usuario ->

//            usuario?.let {
//                nomeUsuario.text = it.nome?: ""
//            }

            nomeUsuario.text = usuario?.nome ?: ""
        }

    }

    private fun realWhereAvancado() {
        val realm = Realm.getDefaultInstance()
//        realm.where(Usuario::class.java).notEqualTo("nome", "")

        val results = realm.where(Usuario::class.java).isNull("nome").findAll()

        Log.d("tag", "${results.size}")
    }
}
