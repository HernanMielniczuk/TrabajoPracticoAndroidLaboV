package Utilities.BuffetMensajes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 16/05/2017.
 */

public class BuffetMensajes extends DialogFragment {

    private int titulo;
    private int mensaje;
    private int botonOk;
    private LinearLayout viewRoot;

    public void setTitulo(int t) { titulo = t; }
    public void setMensaje(int m) { mensaje = m; }
    public void setBotonOk(int b) { botonOk = b; }
    public void setViewRoot(LinearLayout l) { viewRoot = l; }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        BuffetMensajesListener listener = new BuffetMensajesListener();
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton(botonOk, listener);
        View viewAlert = LayoutInflater.from(getContext()).inflate(R.layout.layout_alert, viewRoot, false);
        builder.setView(viewAlert);
        return builder.create();
    }
}
