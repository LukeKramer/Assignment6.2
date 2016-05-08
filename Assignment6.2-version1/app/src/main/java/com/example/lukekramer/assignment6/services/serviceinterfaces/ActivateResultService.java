package com.example.lukekramer.assignment6.services.serviceinterfaces;

/**
 * Created by lukekramer on 07/05/16.
 */
public interface ActivateResultService {

    String activateResultAccount(long clientid, long loanid,String status);

    boolean isAccountActivated();

    boolean deactivateAccount();
}
