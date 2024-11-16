package br.com.greenrank.service.wallet;

import br.com.greenrank.exceptions.WalletNotFoundException;
import br.com.greenrank.exceptions.WalletNotSavedException;
import br.com.greenrank.model.wallet.Wallet;

import java.sql.SQLException;
import java.util.List;

public interface WalletService {
    Wallet create(Wallet wallet) throws SQLException, WalletNotSavedException, UnsupportedOperationException;
    List<Wallet> listAll();
    Wallet update(Wallet wallet) throws SQLException, WalletNotFoundException;
    void delete(Long id) throws SQLException, WalletNotFoundException;
}
