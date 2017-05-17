package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.View;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 16/05/2017.
 */

public class PedidoViewHolder extends ViewHolder {

    public TextView tvNombre;
    public TextView tvPrecio;
    public Button btnQuitar;

    public PedidoViewHolder(View itemView) {
        super(itemView);
        tvNombre = (TextView) itemView.findViewById(R.id.nombreProducto);
        tvPrecio = (TextView) itemView.findViewById(R.id.precioProducto);
        btnQuitar = (Button) itemView.findViewById(R.id.btnProducto);
        btnQuitar.setText(R.string.btnQuitarProducto);
    }
}
