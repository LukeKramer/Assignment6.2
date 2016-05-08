package com.example.lukekramer.assignment6.services.result;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.entity.Result;
import com.example.lukekramer.assignment6.repository.Result.Impl.ResultRepositoryImpl;
import com.example.lukekramer.assignment6.repository.Result.ResultRepository;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateResultService;

/**
 * Created by lukekramer on 07/05/16.
 */
public class ActivateResultServiceImpl extends Service implements ActivateResultService{

    private final IBinder localBinder = new ActivateResultServiceLocalBinder();
    private ResultRepository resultRepository;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateResultServiceLocalBinder extends Binder{

        public ActivateResultServiceImpl getService(){return ActivateResultServiceImpl.this;}
    }

    @Override
    public String activateResultAccount(long clientid, long loanid, String status) {

        if(true)
        {
            Long value = null;
            Result standardResult = new Result.Builder()
                .ClientID(clientid)
                .LoanID(loanid)
                .Status(status)
                .Date(value)
                .Build();

            createResult(standardResult);

        return DomainState.ACTIVATED.name();
        }
        else
        {
        return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isAccountActivated() {
        return resultRepository.findAll().size()>0;
    }

    @Override
    public boolean deactivateAccount() {

        int row = resultRepository.deleteAll();
        return row>0;
    }

    private Result createResult(Result result)
    {
        resultRepository = new ResultRepositoryImpl(this.getApplicationContext());

        return resultRepository.save(result);
    }
}
