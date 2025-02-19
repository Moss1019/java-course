package com.mossonthetree.service;

import com.mossonthetree.model.Account;
import com.mossonthetree.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped()
public class AccountService {
    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public Account create(Account account) {
        return repo.create(account);
    }

    public List<Account> getAll() {
        return repo.getAll();
    }
}
