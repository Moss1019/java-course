package com.mossonthetree.service;

import com.mossonthetree.client.AccountClient;
import com.mossonthetree.model.Trade;
import com.mossonthetree.repository.TradeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.UUID;

@ApplicationScoped()
public class TradeService {
    private final TradeRepository repo;

    private final AccountClient accountClient;

    public TradeService(TradeRepository repo, @RestClient() AccountClient accountClient) {
        this.repo = repo;
        this.accountClient = accountClient;
    }

    public Trade create(Trade trade) {
        var account = accountClient.getById(trade.accountId);
        if(account == null) {
            return null;
        }
        trade.tradeId = UUID.randomUUID().toString();
        trade.isOpen = true;
        return repo.create(trade);
    }

    public List<Trade> get(String accountId) {
        return repo.get(accountId);
    }

    public Trade close(String tradeId) {
        var trade = repo.getById(tradeId);
        if(trade == null) {
            return null;
        }
        trade.isOpen = false;
        return repo.update(trade);
    }

    public Trade delete(String tradeId) {
        return repo.delete(tradeId);
    }
}
