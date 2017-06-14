package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuViewHolder extends ViewHolder{

    public TextView tvNombre;
    public TextView tvPrecio;
    public ImageView ivFoto;
    public Button btnAgregar;

    public MenuViewHolder(View itemView) {
        super(itemView);
        tvNombre = (TextView) itemView.findViewById(R.id.nombreProducto);
        tvPrecio = (TextView) itemView.findViewById(R.id.precioProducto);
        ivFoto = (ImageView) itemView.findViewById(R.id.fotoDefaultProducto);
        btnAgregar = (Button) itemView.findViewById(R.id.btnProducto);
        btnAgregar.setText(R.string.btnAgregarProducto);
    }
}
