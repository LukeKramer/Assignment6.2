package com.example.lukekramer.assignment6.services.client;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.entity.Person;
import com.example.lukekramer.assignment6.repository.person.Impl.PersonRepositoryImpl;
import com.example.lukekramer.assignment6.repository.person.PersonRepository;
import com.example.lukekramer.assignment6.services.serviceinterfaces.ActivateClientService;

/**
 * Created by lukekramer on 04/05/16.
 */
public class ActivateClientServiceImpl extends Service implements ActivateClientService{

    private final IBinder localBinder = new ActivateClientServiceLocalBinder();
    private PersonRepository personRepository;

    public ActivateClientServiceImpl() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }



    public class ActivateClientServiceLocalBinder extends Binder
    {
        public ActivateClientServiceImpl getService()
        {
            return ActivateClientServiceImpl.this;
        }

    }

    @Override
    public String activateClientAccount(long income, String fname, String lname, String phone, String email) {

        if(true) {
            Person standardPerson = new Person.Builder()
                    .Email(email)
                    .FirstName(fname)
                    .Income(income)
                    .LastName(lname)
                    .PhoneNumber(phone)
                    .build();
            createPerson(standardPerson);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public boolean isAccountActivated() {
        return personRepository.findAll().size()>0;
    }

    @Override
    public boolean deactivateAccount() {
        int rows = personRepository.deleteAll();
        return rows > 0;
    }

    private Person createPerson(Person person)
    {

        personRepository = new PersonRepositoryImpl(this.getApplicationContext());

        return personRepository.save(person);
    }




}
