package com.mossonthetree.repository;

import com.mossonthetree.database.Tables;
import com.mossonthetree.database.tables.TBLTrade;
import com.mossonthetree.extension.TradeExtensions;
import com.mossonthetree.model.Trade;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import org.jooq.CloseableDSLContext;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped()
public class TradeRepository {
    private static final TBLTrade TRADE = Tables.TRADE;

    private final CloseableDSLContext ctx;

    public TradeRepository() {
        ctx = DSL.using("jdbc:postgresql://localhost:5432/trade_test_db",
                "postgres",
                "secret123!");
    }

    @PreDestroy()
    public void destroy() {
        ctx.close();
    }

    public Trade create(Trade trade) {
        try {
            var create = ctx.insertInto(TRADE)
                    .columns(TRADE.TRADE_ID,
                            TRADE.ACCOUNT_ID,
                            TRADE.INSTRUMENT,
                            TRADE.BUY_PRICE,
                            TRADE.IS_OPEN)
                    .values(trade.tradeId,
                            trade.accountId,
                            trade.instrument,
                            trade.buyPrice,
                            trade.isOpen);
            create.execute();
            return trade;
        } catch (Exception ex) {
            // TODO: Logging here
            return null;
        }
    }

    public List<Trade> get(String accountId) {
        try {
            var select = ctx
                    .select()
                    .from(TRADE)
                    .where(TRADE.ACCOUNT_ID.eq(accountId));
            var rs = select.fetch();
            return TradeExtensions.fromResult(rs);
        } catch (Exception ex) {
            // TODO: Logging here
            return new ArrayList<>();
        }
    }

    public Trade getById(String tradeId) {
        try {
            var select = ctx
                    .select()
                    .from(TRADE)
                    .where(TRADE.TRADE_ID.eq(tradeId));
            var rs = select.fetch();
            if(rs.isEmpty()) {
                return null;
            }
            return TradeExtensions.fromResult(rs).getFirst();
        } catch (Exception ex) {
            // TODO: Logging here
            return null;
        }
    }

    public Trade update(Trade trade) {
        try {
             var update = ctx
                     .update(TRADE)
                     .set(TRADE.ACCOUNT_ID, trade.accountId)
                     .set(TRADE.INSTRUMENT, trade.instrument)
                     .set(TRADE.BUY_PRICE, trade.buyPrice)
                     .set(TRADE.IS_OPEN, trade.isOpen)
                     .where(TRADE.TRADE_ID.eq(trade.tradeId));
             update.execute();
             return trade;
        } catch (Exception ex) {
            // TODO: Logging here
            return null;
        }
    }

    public Trade delete(String tradeId) {
        try {
            var tradeToDelete = getById(tradeId);
            if(tradeToDelete == null) {
                return null;
            }
            var delete = ctx
                    .deleteFrom(TRADE)
                    .where(TRADE.TRADE_ID.eq(tradeId));
            delete.execute();
            return tradeToDelete;
        } catch (Exception ex) {
            // TODO: Logging here
            return null;
        }
    }
}
