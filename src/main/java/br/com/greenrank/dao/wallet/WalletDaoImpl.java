package br.com.greenrank.dao.wallet;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.exceptions.WalletNotFoundException;
import br.com.greenrank.exceptions.WalletNotSavedException;
import br.com.greenrank.model.wallet.Wallet;
import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WalletDaoImpl implements WalletDao {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Wallet save(Wallet wallet, Connection connection) throws SQLException, WalletNotSavedException {
        final String sql = "BEGIN INSERT INTO T_GR_WALLET (bl_coins, id_user) VALUES (?, ?) RETURNING id_wallet into ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setDouble(1, wallet.getBalance());
        call.setObject(2, wallet.getIdUser());
        call.registerOutParameter(3, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(3);
        if(linhasAlteradas == 0 || id == 0){
            throw new WalletNotSavedException();
        }
        wallet.setId(id);
        return wallet;
    }

    @Override
    public List<Wallet> findAll() {
        final String sql = "SELECT * FROM T_GR_WALLET";
        final List<Wallet> all = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Wallet wallet = new Wallet(
                        resultSet.getLong("id_wallet"),
                        resultSet.getDouble("bl_coins"),
                        resultSet.getLong("id_user")
                );
                all.add(wallet);
            }
        } catch (SQLException e){
            logger.severe("Nenhum saldo registrado: " + e.getMessage());
        }

        return all;
    }

    @Override
    public Wallet update(Wallet wallet, Connection connection) throws SQLException, WalletNotFoundException {
        final String sql = "UPDATE T_GR_WALLET SET bl_coins = ?, id_user = ? WHERE id_wallet = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setDouble(1, wallet.getBalance());
        pstmt.setLong(2, wallet.getIdUser());
        pstmt.setLong(3, wallet.getId());

        int linhasAlteradas = pstmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new WalletNotFoundException();
        }

        return wallet;
    }

    @Override
    public void deleteById(Long id, Connection connection) throws SQLException, WalletNotFoundException {
        final String sql = "DELETE FROM T_GR_WALLET WHERE id_wallet = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        int linhasAlteradas = pstmt.executeUpdate();
        if(linhasAlteradas == 0){
            throw new WalletNotFoundException();
        }
    }
}
