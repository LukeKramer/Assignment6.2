package com.example.lukekramer.assignment6.services.chainloan;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.chain.LoanChainSetup;
import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateLoanResultService;

/**
 * Created by lukekramer on 08/05/16.
 */
//Justification Im using bound service because this service is bound directly to the ActivateClientService
//and ActivitLoan Service is wich used inturn returns the loan status
public class ActivateLoanResultServiceImpl extends Service implements ActivateLoanResultService{

    private final IBinder localBinder = new ActivateLoanResultServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }



    public class ActivateLoanResultServiceLocalBinder extends Binder
    {
        public ActivateLoanResultServiceImpl getService()
        {
            return ActivateLoanResultServiceImpl.this;
        }

    }

    @Override
    public String activateLoanResultAccount(String type, long income) {

        if(true)
        {
            LoanChainSetup.getLoanType(type,income);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }




}
