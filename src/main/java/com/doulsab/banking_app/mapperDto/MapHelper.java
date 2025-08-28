package com.doulsab.banking_app.mapperDto;

import com.doulsab.banking_app.dto.AccountDto;
import com.doulsab.banking_app.entity.Account;

public final class MapHelper {

    // Private constructor to prevent instantiation
    private MapHelper() {
    }

    public static AccountDto mapToDto(Account account) {
        return new AccountDto(account.getId(), account.getAccountHolderName(), account.getAccountBalance(), account.getAccountNumber());
    }

    public static Account mapFromDto(AccountDto dto) {
        return new Account(dto.id(), dto.accountHolderName(), dto.accountBalance(), dto.accountNumber());
    }
}
