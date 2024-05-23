package com.doulsab.banking_app.mapperDto;

import com.doulsab.banking_app.dto.AccountDto;
import com.doulsab.banking_app.entity.Account;

public class MapHelper {

    public static AccountDto mapToDto(Account account) {
        return new AccountDto(account.getId(), account.getAccountHolderName(), account.getAccountBalance(), account.getAccountNumber());
    }

    public static Account mapFromDto(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getAccountHolderName(), accountDto.getAccountBalance(), accountDto.getAccountNumber());
    }

}
