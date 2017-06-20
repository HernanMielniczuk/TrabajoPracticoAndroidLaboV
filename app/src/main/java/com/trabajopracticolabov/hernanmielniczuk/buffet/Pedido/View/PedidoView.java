package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.View;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Controller.LoginController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.IGestionProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.ILogout;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity.PedidoActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;
import java.util.Locale;

import static com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView.productosPedido;

/**
 * Created by Hernan on 16/05/2017.
 */

public class PedidoView implements ILogout, IGestionProducto {

    private PedidoActivity activity;
    private Button btnLogout;
    private FloatingActionButton btnEnviarPedido;
    private TextView tvImportePedido;
    private RecyclerView rv;
    private PedidoAdapter adapter;

    public PedidoView(PedidoActivity a){
        activity = a;
        btnLogout = (Button) a.findViewById(R.id.logout);
        btnEnviarPedido = (FloatingActionButton) a.findViewById(R.id.btnEnviarPedido);
        tvImportePedido = (TextView) a.findViewById(R.id.txtImportePedido);
        rv = (RecyclerView) a.findViewById(R.id.rvProductosPedido);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new PedidoAdapter();
        rv.setAdapter(adapter);
        setListeners();
        actualizarContadores();
    }

    private void setListeners() {
        btnEnviarPedido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                productosPedido.clear();
                Toast.makeText(activity, R.string.msgOrderSent, Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        });
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = rv.indexOfChild(v);
                quitarProducto(productosPedido.get(i));
                actualizarContadores();
                adapter.notifyDataSetChanged();
                if(productosPedido.size() == 0) {
                    Toast.makeText(activity, R.string.msgAllProductsRemoved, Toast.LENGTH_SHORT).show();
                    activity.finish();
                }
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void actualizarContadores() {
        Double sumaPrecios = 0D;
        for(Producto p : productosPedido) {
            sumaPrecios += p.getPrecio();
        }
        tvImportePedido.setText(String.format(Locale.getDefault(), "$%1$.2f", sumaPrecios));
    }

    @Override
    public void logout() {
        LoginController.logout(activity);
        activity.finish();
    }

    @Override
    public void agregarProducto(Producto p) {
        // No aplica a la activity Pedido
    }

    @Override
    public void quitarProducto(Producto p) {
        productosPedido.remove(p);
    }
}
