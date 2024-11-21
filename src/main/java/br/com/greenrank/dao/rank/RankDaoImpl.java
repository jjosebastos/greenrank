package br.com.greenrank.dao.rank;

import br.com.greenrank.config.DatabaseConnectionFactory;
import br.com.greenrank.model.rank.Rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RankDaoImpl implements RankDao {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    @Override
    public List<Rank> listAll() {
        final String sql = "SELECT * FROM T_GR_RANK";
        final List<Rank> ranks = new ArrayList<Rank>();
        try (Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rank rank = new Rank(
                        rs.getLong("id_rank"),
                        rs.getInt("qt_score"),
                        rs.getLong("id_customer"),
                        rs.getLong("id_enterprise")
                );
                ranks.add(rank);
            }
        } catch (SQLException e){
            logger.severe("No records found on Rank");
        }

        return ranks;
    }

    @Override
    public List<Rank> listTopPontuations() {
        final String sql = "SELECT * FROM T_GR_RANK ORDER BY qt_score DESC";
        List<Rank> ranks = new ArrayList<>();
        try(Connection connection = DatabaseConnectionFactory.create().get()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Rank rank = new Rank(
                        rs.getLong("id_rank"),
                        rs.getInt("qt_score"),
                        rs.getLong("id_customer"),
                        rs.getLong("id_enterprise")
                );
                ranks.add(rank);
            }
        } catch (SQLException e){
            logger.severe("No records found on Rank");
        }
        return ranks;
    }
}
