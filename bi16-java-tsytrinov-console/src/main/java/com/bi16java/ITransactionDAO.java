package com.bi16java;

import java.util.List;

/**
 * Created by tsytrin on 04.01.2017.
 */
public interface ITransactionDAO {

    public int delete(int transactionID);
    public List<Transaction> getAll();
    public Transaction get(int transactionID);
    public int insert(Transaction transaction);
    public int update(Transaction transaction);

}
