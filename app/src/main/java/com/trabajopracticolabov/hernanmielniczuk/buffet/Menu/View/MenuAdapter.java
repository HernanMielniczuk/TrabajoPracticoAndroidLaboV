package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;
import java.util.Locale;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuAdapter extends Adapter<MenuViewHolder> implements View.OnClickListener{

    private List<Producto> productos;
    private View.OnClickListener listener;

    public MenuAdapter(List<Producto> p){
        productos = p;
    }

    public void setOnClickListener(View.OnClickListener l) {
        listener = l;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto, parent, false);
        v.setOnClickListener(this);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        Producto p = productos.get(position);
        holder.tvNombre.setText(p.getNombre());
        holder.tvPrecio.setText(String.format(Locale.getDefault(), "$%1$.2f", p.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }
}
