package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 16/05/2017.
 */

public class AlternarTabMenuListener implements View.OnClickListener {

    private IAlternarTab alternador;

    public AlternarTabMenuListener(IAlternarTab a){
        alternador = a;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnMenuBebidas){
            alternador.verBebidas();
        } else if (v.getId() == R.id.btnMenuMenu){
            alternador.verMenu();
        } else if (v.getId() == R.id.btnMenuSnacks){
            alternador.verSnacks();
        }
    }
}
