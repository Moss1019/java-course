package com.mossonthetree.extension;

import com.mossonthetree.database.Tables;
import com.mossonthetree.database.tables.TBLTrade;
import com.mossonthetree.model.Trade;
import org.jooq.Result;

import java.util.ArrayList;
import java.util.List;

public class TradeExtensions {
    private final static TBLTrade TRADE = Tables.TRADE;

    public static List<Trade> fromResult(Result<?> rs) {
        var res = new ArrayList<Trade>();
        for(var r: rs) {
            res.add(new Trade(r.get(TRADE.TRADE_ID).trim(),
                    r.get(TRADE.ACCOUNT_ID).trim(),
                    r.get(TRADE.INSTRUMENT).trim(),
                    r.get(TRADE.BUY_PRICE),
                    r.get(TRADE.IS_OPEN)));
        }
        return res;
    }
}
