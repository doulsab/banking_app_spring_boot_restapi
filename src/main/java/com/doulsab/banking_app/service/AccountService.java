package com.doulsab.banking_app.service;

import com.doulsab.banking_app.dto.AccountDto;
import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getById(Long id);
    void deleteAccount(Long id);
    AccountDto amountDeposit(Long id ,double amountToAdd);
    AccountDto amountWithdraw(Long id ,double amountToRemove);

    List<AccountDto> getAccounts();

}
