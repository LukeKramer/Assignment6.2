package com.example.lukekramer.assignment6.service.result;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.result.ActivateResultServiceImpl;

import junit.framework.Assert;

/**
 * Created by lukekramer on 07/05/16.
 */
public class ActiveResultServiceTest extends AndroidTestCase {
    
    private ActivateResultServiceImpl activateResultService;
    private boolean isBound;
    

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ActivateResultServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }
    
    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ActivateResultServiceImpl.ActivateResultServiceLocalBinder binder
                    = (ActivateResultServiceImpl.ActivateResultServiceLocalBinder) service;
            activateResultService = binder.getService();
            
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

        String activate = activateResultService.activateResultAccount(1,2,"Approved");
        Assert.assertEquals("ACTIVATED",activate);

    }

    public void testIsAccountActivated() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateResultService.activateResultAccount(1,2,"Approved");

        boolean activated = activateResultService.isAccountActivated();

        Assert.assertTrue("ACTIVATED",activated);

    }

    public void testDeactivatedAccount() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        activateResultService.activateResultAccount(1,2,"Approved");

        boolean deactivated = activateResultService.deactivateAccount();

        Assert.assertTrue("NOTACTIVATED",deactivated);


    }
}
