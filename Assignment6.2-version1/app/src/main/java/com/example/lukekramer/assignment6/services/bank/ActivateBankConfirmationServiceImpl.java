package com.example.lukekramer.assignment6.services.bank;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.BankingSetup;
import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateBankConfirmationService;

/**
 * Created by lukekramer on 08/05/16.
 */
//Justification Im using bound service because this service is bound directly to the ActivateClientService
//and uses information from the ClientService to veryfy a user has a banking record
public class ActivateBankConfirmationServiceImpl extends Service implements ActivateBankConfirmationService{

    private IBinder localbinder = new ActivateBankConfirmationServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localbinder;
    }



    public class ActivateBankConfirmationServiceLocalBinder extends Binder
    {
        public ActivateBankConfirmationServiceImpl getService()
        {
            return ActivateBankConfirmationServiceImpl.this;
        }

    }

    @Override
    public String activateBankConfirmationAccount(String accountnumber) {

        if(true) {

            BankingSetup.ConfirmBankAccount(accountnumber);
            return DomainState.ACTIVATED.name();
        }
       return DomainState.NOTACTIVATED.name();
    }







}
