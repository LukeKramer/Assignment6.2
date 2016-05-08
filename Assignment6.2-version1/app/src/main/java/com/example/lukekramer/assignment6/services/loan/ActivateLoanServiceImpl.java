package com.example.lukekramer.assignment6.services.loan;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.repository.loan.Impl.LoanRepositoryImpl;
import com.example.lukekramer.assignment6.repository.loan.LoanRepository;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateLoanService;

/**
 * Created by lukekramer on 06/05/16.
 */
public class ActivateLoanServiceImpl extends Service implements ActivateLoanService{

    private final IBinder localBinder = new ActivateLoanServiceLocalBinder();
    private LoanRepository loanRepository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateLoanServiceLocalBinder extends Binder
    {

        public ActivateLoanServiceImpl getService(){return ActivateLoanServiceImpl.this;}
    }

    @Override
    public String activateLoanAccount(long maxAmount, long minAmount) {
        if(true)
        {
            Loan standardLoan = new Loan.Builder()
                    .maxLoanAmount(maxAmount)
                    .minLoanAmount(minAmount)
                    .build();

            createLoan(standardLoan);
            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }


    }

    @Override
    public boolean isAccountActivated() {
        return loanRepository.findAll().size()>0;
    }

    @Override
    public boolean deactivateAccount() {

        int row = loanRepository.deleteAll();
        return row>0;
    }

    private Loan createLoan(Loan loan)
    {
        loanRepository = new LoanRepositoryImpl(this.getApplicationContext());

        return loanRepository.save(loan);
    }
}
