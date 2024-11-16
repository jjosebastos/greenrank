package br.com.greenrank.service.wallet;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.dao.wallet.WalletDao;
import br.com.greenrank.dao.wallet.WalletDaoFactory;
import br.com.greenrank.exceptions.WalletNotFoundException;
import br.com.greenrank.exceptions.WalletNotSavedException;
import br.com.greenrank.model.wallet.Wallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class WalletServiceImpl implements WalletService {
    private final WalletDao dao = WalletDaoFactory.create();
    @Override
    public Wallet create(Wallet wallet) throws SQLException, WalletNotSavedException, UnsupportedOperationException {
        if(wallet.getId() == null){
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                wallet = this.dao.save(wallet, connection);
                return wallet;

            } catch (SQLException | WalletNotSavedException e ) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public List<Wallet> listAll() {
        return this.dao.findAll();
    }

    @Override
    public Wallet update(Wallet wallet) throws SQLException, WalletNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        wallet = this.dao.update(wallet, connection);
        connection.commit();
        return wallet;
    }

    @Override
    public void delete(Long id) throws SQLException, WalletNotFoundException {
        Connection connection = DatabaseConnectionFactory.create().get();
        this.dao.deleteById(id, connection);
        connection.commit();
    }
}
