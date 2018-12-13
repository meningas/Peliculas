package org.uqbar.peliculasapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_pelicula_detail.*


class PeliculaDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_detail)
        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = PeliculaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        PeliculaDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(PeliculaDetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.pelicula_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, PeliculaListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
