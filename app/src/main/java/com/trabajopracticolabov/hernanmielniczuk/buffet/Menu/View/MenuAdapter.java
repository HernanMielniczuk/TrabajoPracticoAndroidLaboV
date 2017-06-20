package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.View;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trabajopracticolabov.hernanmielniczuk.buffet.DAO.Conexion;
import com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Model.Producto;
import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Hernan on 13/05/2017.
 */

class MenuAdapter extends Adapter<MenuViewHolder> implements View.OnClickListener, Handler.Callback{

    private List<Producto> productos;
    private View.OnClickListener listener;
    private ExecutorService executor;
    private Handler handler;
    private Producto productoActual;
    private MenuViewHolder menuViewHolder;

    MenuAdapter(List<Producto> p){
        productos = p;
        handler = new Handler(this);
    }

    void setOnClickListener(View.OnClickListener l) {
        listener = l;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        executor = Executors.newFixedThreadPool(10);
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_producto, parent, false);
        v.setOnClickListener(this);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        menuViewHolder = holder;
        //final Producto p = productos.get(position);
        productoActual = productos.get(position);
        menuViewHolder.tvNombre.setText(productoActual.getNombre());
        menuViewHolder.tvPrecio.setText(String.format(Locale.getDefault(), "$%1$.2f", productoActual.getPrecio()));
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                try {
                    Conexion conexion = new Conexion();
                    byte[] respuesta = conexion.getBytesDataByGet(productoActual.getUrlImagen());
                    message.arg1 = 1;
                    message.obj = respuesta;
                    handler.sendMessage(message);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
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

    @Override
    public boolean handleMessage(Message msg) {
        byte[] bytes = (byte[]) msg.obj;
        productoActual.setImagen(bytes);
        menuViewHolder.ivFoto.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
        //Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //img.setImageBitmap(bitmap);
        return true;
    }
}
