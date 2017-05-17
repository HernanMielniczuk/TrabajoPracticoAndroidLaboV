package com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.View;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Activity.LoginActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.IGestionProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.ILogout;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity.PedidoActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.util.List;

/**
 * Created by Hernan on 16/05/2017.
 */

public class PedidoView implements ILogout, IGestionProducto {

    private PedidoActivity activity;
    private List<Producto> productos;
    private Button btnLogout;
    private Button btnEnviarPedido;
    private TextView tvImportePedido;
    private RecyclerView rv;

    public PedidoView(PedidoActivity a, List<Producto> p){
        activity = a;
        productos = p;
        btnLogout = (Button) a.findViewById(R.id.logout);
        btnEnviarPedido = (Button) a.findViewById(R.id.btnEnviarPedido);
        tvImportePedido = (TextView) a.findViewById(R.id.txtImportePedido);
        rv = (RecyclerView) a.findViewById(R.id.rvProductosPedido);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        cargarListaPedido();
    }

    private void cargarListaPedido() {
        PedidoAdapter adapter = new PedidoAdapter(productos);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i = rv.indexOfChild(v);
                quitarProducto(productos.get(i));
                actualizarContadores();
            }
        });
        adapter.notifyDataSetChanged();
        rv.setAdapter(adapter);
    }

    private void actualizarContadores(){
        // TODO actualizar el precio total del pedido.
    }

    @Override
    public void logout() {
        //TODO logout a la activity de login
        LoginActivity.logout(activity);
        activity.finish();
    }

    @Override
    public void agregarProducto(Producto p) {
        // No aplica a la activity Pedido
    }

    @Override
    public void quitarProducto(Producto p) {
        productos.remove(p);
    }
}
