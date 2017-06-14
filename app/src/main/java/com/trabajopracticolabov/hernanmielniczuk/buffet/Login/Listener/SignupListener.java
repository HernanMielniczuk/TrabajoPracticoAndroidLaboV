package com.trabajopracticolabov.hernanmielniczuk.buffet.Login.Listener;

import android.view.View;

import com.trabajopracticolabov.hernanmielniczuk.buffet.R;

/**
 * Created by Hernan on 04/06/2017.
 */

public class SignupListener implements View.OnClickListener {

    private ISignup signup;

    public SignupListener(ISignup s){
        signup = s;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLoginRegistrarme){
            signup.signup();
        }
    }
}
