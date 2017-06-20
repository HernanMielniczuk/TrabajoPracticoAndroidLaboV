package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity.MenuActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.IAlternarTab;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.IGestionProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.ILogout;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.IVerPedido;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.CategoriaProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Utilities.BuffetMensajes.BuffetMensajes;

/**
 * Created by Hernan on 13/05/2017.
 */

public class MenuView implements ILogout, IVerPedido, IAlternarTab, IGestionProducto {

    private MenuActivity activity;
    private FloatingActionButton btnVerPedido;
    private Button btnLogout;
    private Button btnListarBebidas;
    private Button btnListarMenu;
    private Button btnListarSnacks;
    private TextView tvPrecioTotal;
    private TextView tvCantidadProductos;
    private RecyclerView rv;
    private List<Producto> productoBebida;
    private List<Producto> productoMenu;
    private List<Producto> productoSnack;
    public static List<Producto> productosPedido;
    private Producto productoActual;

    public MenuView(MenuActivity a){
        activity = a;
        btnVerPedido = (FloatingActionButton) a.findViewById(R.id.btnVerPedido);
        btnLogout = (Button) a.findViewById(R.id.logout);
        btnListarBebidas = (Button) a.findViewById(R.id.btnMenuBebidas);
        btnListarMenu = (Button) a.findViewById(R.id.btnMenuMenu);
        btnListarSnacks = (Button) a.findViewById(R.id.btnMenuSnacks);
        tvCantidadProductos = (TextView) a.findViewById(R.id.txtCantidadProductos);
        tvPrecioTotal = (TextView) a.findViewById(R.id.txtImporte);
        rv = (RecyclerView) a.findViewById(R.id.rvProductos);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        productoBebida = new ArrayList<>();
        productoMenu = new ArrayList<>();
        productoSnack = new ArrayList<>();
        productosPedido = new ArrayList<>();
    }

    public void cargarListas(List<Producto> productos){
        for(Producto p : productos) {
            if (p.getCategoría() == CategoriaProducto.Bebida) {
                productoBebida.add(p);
            } else if (p.getCategoría() == CategoriaProducto.Menu) {
                productoMenu.add(p);
            } else if (p.getCategoría() == CategoriaProducto.Snack) {
                productoSnack.add(p);
            }
        }
    }

    public void setListeners(MenuController c){
        btnVerPedido.setOnClickListener(c.getVerPedidoListener());
        btnLogout.setOnClickListener(c.getLogoutListener());
        btnListarBebidas.setOnClickListener(c.getAlternarTabMenuListener());
        btnListarMenu.setOnClickListener(c.getAlternarTabMenuListener());
        btnListarSnacks.setOnClickListener(c.getAlternarTabMenuListener());
    }

    private void actualizarContadores(Producto p){
        tvCantidadProductos.setText(((Integer)productosPedido.size()).toString());
        if (tvPrecioTotal.getText().toString().isEmpty()){
            tvPrecioTotal.setText(String.format(Locale.getDefault(), "$%1$.2f", p.getPrecio()));
        } else {
            String precioParseado = tvPrecioTotal.getText().toString().substring(1).replace(',','.');
            Double precioTotalActual = Double.parseDouble(precioParseado);
            Double precioProducto = p.getPrecio();
            Double precioAcumulado = precioTotalActual + precioProducto;
            tvPrecioTotal.setText(String.format(Locale.getDefault(), "$%1$.2f", precioAcumulado));
        }
    }

    private void cargarImagenProducto(byte[] img){
        productoActual.setImagen(img);
    }

    @Override
    public void logout() {
        LoginController.logout(activity);
        activity.finish();
    }

    @Override
    public void verPedido() {
        if(productosPedido.size() > 0){
            MenuController.irAPedido(activity);
        } else {
            BuffetMensajes mensaje = new BuffetMensajes();
            mensaje.setTitulo(R.string.msgEmptyProductsListTitle);
            mensaje.setMensaje(R.string.msgEmptyProductsList);
            mensaje.setBotonOk(R.string.btnOK);
            mensaje.setViewRoot((LinearLayout)activity.findViewById(R.id.layout_menu));
            mensaje.show(activity.getSupportFragmentManager(), "normal");
        }
    }

    @Override
    public void agregarProducto(Producto p) {
        productosPedido.add(p);
    }

    @Override
    public void quitarProducto(Producto p) {
        //No utilizado en Menu
    }

    @Override
    public void verBebidas() {
        MenuAdapter adapter = new MenuAdapter(productoBebida);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = rv.indexOfChild(v);
                productoActual = productoBebida.get(i);
                agregarProducto(productoActual);
                actualizarContadores(productoActual);
            }
        });
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    @Override
    public void verMenu() {
        MenuAdapter adapter = new MenuAdapter(productoMenu);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = rv.indexOfChild(v);
                productoActual = productoMenu.get(i);
                agregarProducto(productoActual);
                actualizarContadores(productoActual);
            }
        });
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    @Override
    public void verSnacks() {
        MenuAdapter adapter = new MenuAdapter(productoSnack);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = rv.indexOfChild(v);
                productoActual = productoSnack.get(i);
                agregarProducto(productoActual);
                actualizarContadores(productoActual);
            }
        });
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }
}
