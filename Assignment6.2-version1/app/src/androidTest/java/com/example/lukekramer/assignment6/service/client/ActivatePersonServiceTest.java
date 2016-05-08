package com.example.lukekramer.assignment6.service.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.client.ActivateClientServiceImpl;

import junit.framework.Assert;

/**
 * Created by lukekramer on 04/05/16.
 */
public class ActivatePersonServiceTest extends AndroidTestCase {


    private ActivateClientServiceImpl activateClientService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ActivateClientServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            ActivateClientServiceImpl.ActivateClientServiceLocalBinder binder
                    = (ActivateClientServiceImpl.ActivateClientServiceLocalBinder)service;
            activateClientService = binder.getService();

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


        String activate = activateClientService.activateClientAccount(100000,"Luke","Kramer","071223456","luke@gmail.com");
        Assert.assertEquals("ACTIVATED",activate);


       // this.getContext().unbindService(connection);

    }

    public void testIsAccountActivated() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateClientService.activateClientAccount(100000,"Luke","Kramer","071223456","luke@gmail.com");
       Boolean activated = activateClientService.isAccountActivated();
       Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivatedAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateClientService.activateClientAccount(100000,"Luke","Kramer","071223456","luke@gmail.com");

        Boolean deactivated = activateClientService.deactivateAccount();
        Assert.assertTrue("NOTACTIVATED",deactivated);

    }


}
