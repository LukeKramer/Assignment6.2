package com.example.lukekramer.assignment6.services.username;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.config.util.UsernameSetup;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateUserNameService;

/**
 * Created by lukekramer on 08/05/16.
 */
//Justification Im using bound service because this service is bound directly to the ActivateLoginService
//and is used Variables from the ActivateClientService to create a username
public class ActivateUserNameServiceImpl extends Service implements ActivateUserNameService{
    private IBinder localBinder = new ActivateUserNameServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }




    public class ActivateUserNameServiceLocalBinder extends Binder
    {
        public ActivateUserNameServiceImpl getService()
        {
            return ActivateUserNameServiceImpl.this;
        }

    }

    @Override
    public String activateUsernameAccount(String fname, String lname,long userid) {

        if(true) {
            UsernameSetup.createUsername(fname, lname, userid);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }


}
