package com.example.lukekramer.assignment6.service.loan;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.loan.ActivateLoanServiceImpl;

import junit.framework.Assert;

/**
 * Created by lukekramer on 06/05/16.
 */
public class ActivateLoanServiceTest extends AndroidTestCase {

    private ActivateLoanServiceImpl activateLoanService;
    private boolean isBound;

   public void setUp() throws Exception{
       super.setUp();
       Intent intent = new Intent (this.getContext(), ActivateLoanServiceImpl.class);
       this.getContext().bindService(intent,connection, Context.BIND_AUTO_CREATE);

   }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

           ActivateLoanServiceImpl.ActivateLoanServiceLocalBinder binder
                   = (ActivateLoanServiceImpl.ActivateLoanServiceLocalBinder) service;
            activateLoanService = binder.getService();

            isBound = true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testActivateAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        String activate = activateLoanService.activateLoanAccount(100000,10000);
        Assert.assertEquals("ACTIVATED",activate);

    }

    public void testIsActivateAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateLoanService.activateLoanAccount(100000,10000);
        Boolean activated = activateLoanService.isAccountActivated();

        Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivatedAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateLoanService.activateLoanAccount(100000,10000);
        Boolean deactivated = activateLoanService.deactivateAccount();

        Assert.assertTrue("NOTACTIVATED",deactivated);

    }
}
