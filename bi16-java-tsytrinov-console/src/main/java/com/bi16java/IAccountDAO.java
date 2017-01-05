package com.bi16java;

import java.util.List;

/**
 * Created by tsytrin on 03.01.2017.
 */
public interface IAccountDAO {

    public int delete(int accountID);
    public List<Account> getAll();
    public Account get(int accountID);
    public int insert(Account account);
    public int update(Account account);

}
