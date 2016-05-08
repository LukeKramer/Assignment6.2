package com.example.lukekramer.assignment6.services.password;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.config.util.UserpasswordSetup;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateUserPasswordService;

/**
 * Created by lukekramer on 08/05/16.
 */
//Justification Im using bound service because this service is bound directly to the ActivateLoginService
//and is used to create and hash a password
public class ActivateUserPasswordServiceImpl extends Service implements ActivateUserPasswordService{
    private IBinder localBinder = new ActivateUserPasswordServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateUserPasswordServiceLocalBinder extends Binder {

        public ActivateUserPasswordServiceImpl getService(){return ActivateUserPasswordServiceImpl.this;}
    }

    @Override
    public String activateUserPasswordAccount(String password) {

        if(true)
        {
            UserpasswordSetup.checkPassword(password);

            return DomainState.ACTIVATED.name();
        }
        else {
            return DomainState.NOTACTIVATED.name();
        }
    }
}
