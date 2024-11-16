package br.com.greenrank.dao.wallet;

import br.com.greenrank.exceptions.WalletNotFoundException;
import br.com.greenrank.exceptions.WalletNotSavedException;
import br.com.greenrank.model.wallet.Wallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface WalletDao {
    Wallet save(Wallet wallet, Connection connection) throws SQLException, WalletNotSavedException;
    List<Wallet> findAll();
    Wallet update(Wallet wallet, Connection connection) throws SQLException, WalletNotFoundException;
    void deleteById(Long id, Connection connection) throws SQLException, WalletNotFoundException;
}
