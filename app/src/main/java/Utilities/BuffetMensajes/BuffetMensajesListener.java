package Utilities.BuffetMensajes;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Hernan on 16/05/2017.
 */

public class BuffetMensajesListener implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        //if(which == AlertDialog.BUTTON_NEGATIVE) {}
        //if(which == AlertDialog.BUTTON_NEUTRAL) {}
        if(which == AlertDialog.BUTTON_POSITIVE) {
            dialog.dismiss();
        }
    }
}

