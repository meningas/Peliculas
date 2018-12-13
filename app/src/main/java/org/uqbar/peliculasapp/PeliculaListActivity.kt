package org.uqbar.peliculasapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_list_content.view.*
import org.uqbar.peliculasapp.domain.Pelicula
import org.uqbar.peliculasapp.repo.RepoPeliculas


class PeliculaListActivity : AppCompatActivity() {


    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pelicula_list)

        setSupportActionBar(toolbar)
        toolbar.title = title


        if (pelicula_detail_container != null) {
            twoPane = true
        }

        setupRecyclerView(pelicula_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = PeliculaAdapter(
            this,
            RepoPeliculas.getPeliculas(null, 10),
            twoPane
        )
    }

    class PeliculaAdapter(
        private val parentActivity: PeliculaListActivity,
        private val values: List<Pelicula>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<PeliculaAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Pelicula
                if (twoPane) {
                    val fragment = PeliculaDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.pelicula_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, PeliculaDetailActivity::class.java).apply {
                        putExtra(PeliculaDetailFragment.ARG_ITEM_ID, item.id.toString())
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.pelicula_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val pelicula = values[position]
            holder.peliculaView.text = pelicula.titulo
            holder.actoresView.text = pelicula.actores

            with(holder.itemView) {
                tag = pelicula
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val peliculaView: TextView = view.lblPelicula
            val actoresView: TextView = view.lblActores
        }
    }
}
