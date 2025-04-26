package com.mossonthetree.service;

import com.mossonthetree.model.Trade;
import com.mossonthetree.repository.TradeRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped()
public class TradeService {
    private final TradeRepository repo;

    public TradeService(TradeRepository repo) {
        this.repo = repo;
    }

    public Trade create(Trade trade) {
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
