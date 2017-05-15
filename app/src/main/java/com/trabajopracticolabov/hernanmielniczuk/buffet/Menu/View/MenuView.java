package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TabHost;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.CategoriaProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuView {

    private MenuActivity activity;

    public MenuView(MenuActivity a){
        activity = a;
        prepararTabHost();
    }

    public void bindearRecyclerViewConAdapter(List<Producto> productos){

        RecyclerView rvBebida = (RecyclerView)activity.findViewById(R.id.rvProductosBebida);
        RecyclerView rvMenu = (RecyclerView)activity.findViewById(R.id.rvProductosMenu);
        RecyclerView rvSnack = (RecyclerView)activity.findViewById(R.id.rvProductosSnack);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        //rv.setLayoutManager(layoutManager);

        rvBebida.setLayoutManager(new LinearLayoutManager(activity));
        rvMenu.setLayoutManager(new LinearLayoutManager(activity));
        rvSnack.setLayoutManager(new LinearLayoutManager(activity));

        List<Producto> productoBebida = new ArrayList<>();
        List<Producto> productoMenu = new ArrayList<>();
        List<Producto> productoSnack = new ArrayList<>();
        for(Producto p : productos) {
            if (p.getCategoría() == CategoriaProducto.Bebida) {
                productoBebida.add(p);
            } else if (p.getCategoría() == CategoriaProducto.Menu) {
                productoMenu.add(p);
            } else if (p.getCategoría() == CategoriaProducto.Snack) {
                productoSnack.add(p);
            }
        }

        MenuAdapter adapterBebida = new MenuAdapter(productoBebida);
        MenuAdapter adapterMenu = new MenuAdapter(productoMenu);
        MenuAdapter adapterSnack = new MenuAdapter(productoSnack);

        rvBebida.setAdapter(adapterBebida);
        rvMenu.setAdapter(adapterMenu);
        rvSnack.setAdapter(adapterSnack);
    }

    private void prepararTabHost(){
        TabHost host = (TabHost) activity.findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Café/Bebidas");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Café/Bebidas");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Menú");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Menú");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Snacks");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Snacks");
        host.addTab(spec);
    }
}
