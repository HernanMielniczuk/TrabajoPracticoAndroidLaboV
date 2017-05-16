package com.trabajopracticolabov.hernanmielniczuk.buffet.Menu.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 15/05/2017.
 */

public class LogoutListener implements View.OnClickListener {

    private ILogout logout;

    public LogoutListener(ILogout l){
        logout = l;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.logout){
            logout.logout();
        }
    }
}
