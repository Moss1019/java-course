
-- create trade
create table trade (
    trade_id char(32) primary key,
    account_id char(32),
    instrument char(16),
    buy_price double precision,
    is_open boolean
);
