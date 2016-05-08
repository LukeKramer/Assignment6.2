package com.example.lukekramer.assignment6.services.serviceinterfaces;

/**
 * Created by lukekramer on 04/05/16.
 */
public interface ActivateClientService {

    String activateClientAccount(long income, String fname, String lname, String phone, String email);

    boolean isAccountActivated();

    boolean deactivateAccount();
}
