package com.doulsab.banking_app.service.impl;

import com.doulsab.banking_app.dto.AccountDto;
import com.doulsab.banking_app.entity.Account;
import com.doulsab.banking_app.mapperDto.MapHelper;
import com.doulsab.banking_app.repository.AccountRepository;
import com.doulsab.banking_app.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = MapHelper.mapFromDto(accountDto);
        Account savedAccount = accountRepository.save(account);
        return MapHelper.mapToDto(savedAccount);
    }

    @Override
    public AccountDto getById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        return MapHelper.mapToDto(account);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto amountDeposit(Long id, double amountToAdd) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        double oldBalance = account.getAccountBalance();
        double totalAmount = oldBalance + amountToAdd;
        account.setAccountBalance(totalAmount);
        Account savedAccountIs = accountRepository.save(account);
        return MapHelper.mapToDto(savedAccountIs);
    }

    @Override
    public AccountDto amountWithdraw(Long id, double amountToRemove) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exists"));
        double oldBalance = account.getAccountBalance();

        if (oldBalance < amountToRemove) {
            throw new RuntimeException("Insufficient found");
        }
        double totalAmount = oldBalance - amountToRemove;
        account.setAccountBalance(totalAmount);
        Account savedAccountIs = accountRepository.save(account);
        return MapHelper.mapToDto(savedAccountIs);
    }

    @Override
    public List<AccountDto> getAccounts() {
        List<Account> listAccounts = accountRepository.findAll();
        return listAccounts.stream().map(MapHelper::mapToDto).collect(Collectors.toList());
    }


}
