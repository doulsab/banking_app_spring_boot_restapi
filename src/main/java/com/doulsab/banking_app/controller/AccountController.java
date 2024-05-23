package com.doulsab.banking_app.controller;

import com.doulsab.banking_app.dto.AccountDto;
import com.doulsab.banking_app.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;

    public AccountController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    //  1. Save account to Database via Post method
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> saveAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountServiceImpl.createAccount(accountDto), HttpStatus.CREATED);//201
    }

    // 2. Get account details by I'd
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> getDetailsById(@PathVariable Long id) {
        return new ResponseEntity<>(accountServiceImpl.getById(id), HttpStatus.CREATED);//201    }
    }
    // 3. Delete by Id

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        accountServiceImpl.deleteAccount(id);
        return ResponseEntity.ok("Deleted Successfully!" + id);
    }

    //  4. Deposit by id
    @PutMapping(value = "deposit/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> depositMoney(@PathVariable Long id, @RequestBody Map<String, Double> requestedAmount) {
        double amountToDeposit = requestedAmount.get("amount");
        return new ResponseEntity<>(accountServiceImpl.amountDeposit(id, amountToDeposit), HttpStatus.ACCEPTED);
    }

    // 5. Withdraw by id
    @PutMapping(value = "withdraw/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable Long id, @RequestBody Map<String, Double> requestedAmount) {
        double amountToWithdraw = requestedAmount.get("amount");
        return new ResponseEntity<>(accountServiceImpl.amountWithdraw(id, amountToWithdraw), HttpStatus.ACCEPTED);
    }

    //  6. List All accounts
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return new ResponseEntity<>(accountServiceImpl.getAccounts(), HttpStatus.ACCEPTED);
    }
}