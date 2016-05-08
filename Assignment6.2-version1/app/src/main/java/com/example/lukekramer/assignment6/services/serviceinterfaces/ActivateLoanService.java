package com.example.lukekramer.assignment6.services.serviceinterfaces;

/**
 * Created by lukekramer on 06/05/16.
 */
public interface ActivateLoanService {

    String activateLoanAccount(long maxAmount, long minAmount);

    boolean isAccountActivated();

    boolean deactivateAccount();


}
