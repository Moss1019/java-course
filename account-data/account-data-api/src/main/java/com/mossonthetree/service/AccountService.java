package com.mossonthetree.service;

import com.mossonthetree.model.Account;
import com.mossonthetree.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped()
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account create(Account account) {
        account.accountId = UUID.randomUUID().toString();
        return repo.create(account);
    }

    public List<Account> getAll() {
        return repo.getAll();
    }

    public Account getById(String accountId) {
        return repo.getById(accountId);
    }

    public Account delete(String accountId) {
        return repo.delete(accountId);
    }

    public Account update(Account account) {
        List<Account> accounts = getAll();
        boolean found = accounts.stream().anyMatch((Account a) -> {
            return a.userName.equals(account.userName);
        });
        if(found) {
            return repo.update(account);
        }
        return account;
    }
}
