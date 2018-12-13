package org.uqbar.peliculasapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_pelicula_detail.*
import kotlinx.android.synthetic.main.activity_pelicula_list.*
import kotlinx.android.synthetic.main.pelicula_detail.view.*
import org.uqbar.peliculasapp.domain.Pelicula
import org.uqbar.peliculasapp.repo.RepoPeliculas



class PeliculaDetailFragment : Fragment() {

    private var item: Pelicula? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                item = RepoPeliculas.getPelicula(it.getString(ARG_ITEM_ID)!!.toLong())
                activity?.toolbar_layout?.title = item?.titulo
                activity?.toolbar?.title = item?.titulo
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pelicula_detail, container, false)

        item?.let {
            rootView.pelicula_actores.text = it.actores
            rootView.pelicula_sinopsis.text = it.sinopsis
            rootView.pelicula_genero.text = it.descripcionGenero
            rootView.imgGenero.setImageDrawable(resources.getDrawable(
                    GeneroAdapter.getIconoGenero(it), null))
        }

        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
