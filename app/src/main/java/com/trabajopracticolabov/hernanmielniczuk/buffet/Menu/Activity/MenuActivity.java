package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuController;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Controller.MenuControllerThread;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.AlternarTabMenuListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.LogoutListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener.VerPedidoListener;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.CategoriaProducto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View.MenuView;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Pedido.Activity.PedidoActivity;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utilities.ActionBarHelper;
import Utilities.JSONParser.BuffetJSONParser;

public class MenuActivity extends AppCompatActivity implements Handler.Callback {

    //public List<Producto> productos;
    private MenuView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBarHelper.invalidateActionBar(this);

       /* productos = new ArrayList<>();
        productos.add(new Producto("Pizza mozzarella porción", 20.5, CategoriaProducto.Menu));
        productos.add(new Producto("Coca-Cola 1.5 lts", 35D, CategoriaProducto.Bebida));
        productos.add(new Producto("Hamburguesa completa", 30D, CategoriaProducto.Menu));
        productos.add(new Producto("Caramelos surtidos", 15.4, CategoriaProducto.Snack));
        productos.add(new Producto("Agua saborizada 500cc", 16D, CategoriaProducto.Bebida));
        productos.add(new Producto("Pizza fugazzetta porción", 20.5, CategoriaProducto.Menu));
        productos.add(new Producto("Sprite 1.5 lts", 35D, CategoriaProducto.Bebida));
        productos.add(new Producto("Hamburguesa lech y tom", 25D, CategoriaProducto.Menu));
        productos.add(new Producto("Turrón maní", 6D, CategoriaProducto.Snack));
        productos.add(new Producto("Citric 500cc", 20D, CategoriaProducto.Bebida));
        productos.add(new Producto("Empanada carne", 15D, CategoriaProducto.Menu));
        productos.add(new Producto("Empanada pollo", 15D, CategoriaProducto.Menu));
        productos.add(new Producto("Hamburguesa lech y tom", 25D, CategoriaProducto.Menu));
        productos.add(new Producto("Turrón maní", 6D, CategoriaProducto.Snack));
        productos.add(new Producto("Papas fritas", 25.8, CategoriaProducto.Snack));
        productos.add(new Producto("Citric 500cc", 25D, CategoriaProducto.Bebida));
        productos.add(new Producto("Gaseosa Tónica 500cc", 18D, CategoriaProducto.Bebida));
        productos.add(new Producto("Gaseosa Pomelo 500cc", 18D, CategoriaProducto.Bebida));
        productos.add(new Producto("Agua min 500cc", 15D, CategoriaProducto.Bebida));   */




        view = new MenuView(this);
        //view.cargarListas(productos);
        MenuController c = new MenuController(this, view, new AlternarTabMenuListener(view), new VerPedidoListener(view), new LogoutListener(view));
        view.setListeners(c);

        Handler h = new Handler(this);
        Thread t = new Thread(new MenuControllerThread("http://192.168.0.8:3000/productos", h), "ThreadQueEstaEnLaActivity");
        t.start();
    }

    public void verPedido(List<Producto> p){
        Intent intent = new Intent(this, PedidoActivity.class);
        intent.putExtra("pedido",(Serializable) p);
        this.startActivity(intent);
    }

   @Override
   public boolean handleMessage(Message msg) {
       if(msg.arg1 == 1){
           BuffetJSONParser parser = new BuffetJSONParser();
           try {
               List<Producto> prod = parser.parsearProductos((String) msg.obj);
               view.cargarListas(prod);
           } catch (JSONException e) {
               e.printStackTrace();
           }
       }
       return true;
   }
}
