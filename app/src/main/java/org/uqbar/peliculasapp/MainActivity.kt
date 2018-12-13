package org.uqbar.peliculasapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun inicio(view: View){
        val Inicio = Intent(applicationContext, PeliculaListActivity::class.java)
        startActivity(Inicio)
    }
}
