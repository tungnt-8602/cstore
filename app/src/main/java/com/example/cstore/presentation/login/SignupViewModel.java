package com.example.cstore.presentation.login;

import com.example.cstore.common.base.BaseViewModel;
import com.example.cstore.model.Account;

public class SignupViewModel extends BaseViewModel {

    public Account getAccount(){
        return local.getRegisteredAccount();
    }

    public void saveAccount(Account account){
        local.saveRegisteredAccount(account);
    }
}
