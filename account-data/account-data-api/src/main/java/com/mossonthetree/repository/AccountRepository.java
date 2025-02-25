package com.mossonthetree.repository;

import com.google.gson.Gson;
import com.mossonthetree.file.FileHandler;
import com.mossonthetree.model.Account;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped()
public class AccountRepository {
    private final List<String> fileNames = new ArrayList<>();

    @PostConstruct()
    public void init() {
        String fileName = "accounts.json";
        FileHandler handler = new FileHandler();
        String content = handler.load(fileName);
        if (!content.isEmpty()) {
            List<String> parsed = new Gson().fromJson(content, List.class);
            for (String file : parsed) {
                fileNames.add(file);
            }
        }
    }

    @PreDestroy()
    public void destroy() {
        String fileName = "accounts.json";
        FileHandler handler = new FileHandler();
        String content = new Gson().toJson(fileNames);
        handler.save(fileName, content);
    }

    public Account create(Account account) {
        Gson gson = new Gson();
        String json = gson.toJson(account);
        FileHandler handler = new FileHandler();
        String fileName = account.userName + ".json";
        handler.save(fileName, json);
        fileNames.add(fileName);
        return account;
    }

    public List<Account> getAll() {
        FileHandler handler = new FileHandler();
        List<Account> res = new ArrayList<>();
        Gson gson = new Gson();
        for (String fileName : fileNames) {
            String json = handler.load(fileName);
            Account account = gson.fromJson(json, Account.class);
            res.add(account);
        }
        return res;
    }

    public Account delete(String userName) {
        String fileName = String.format("%s.json", userName);
        if(fileNames.contains(fileName)) {
            List<Account> accounts = getAll();
            Account account = accounts
                    .stream()
                    .filter((Account a) -> {
                        return a.userName.equals(userName);
                    })
                    .findFirst()
                    .get();
            fileNames.remove(fileName);
            FileHandler handler = new FileHandler();
            handler.delete(fileName);
            return account;
        }
        return null;
    }

    public Account update(Account account) {
        String fileName = String.format("%s.json", account.userName);
        Gson gson = new Gson();
        String json = gson.toJson(account);
        FileHandler handler = new FileHandler();
        handler.save(fileName, json);
        return account;
    }
}
