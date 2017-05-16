package Utilities.BuffetMensajes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 16/05/2017.
 */

public class BuffetMensajes extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        BuffetMensajesListener listener = new BuffetMensajesListener();
        builder.setPositiveButton(R.string.btnEmptyProductsListOK, listener);
        View viewAlert = LayoutInflater.from(getContext()).inflate(R.layout.layout_alert, null);
        builder.setView(viewAlert);
        return builder.create();
    }
}
