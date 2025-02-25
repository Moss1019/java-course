package com.mossonthetree.repository;

import com.mossonthetree.database.Tables;
import com.mossonthetree.database.tables.TBLTrade;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped()
public class TradeRepository {
    private static final TBLTrade TRADE = Tables.TRADE;


}
