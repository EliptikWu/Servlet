package repository.SaleRepositoryImpl;

import Config.DataBaseConnection;
import model.Sale;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaleRepositoryDBImpl implements Repository<Sale> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    private Sale createSale(ResultSet resultSet) throws SQLException {
        Sale sale = new Sale();
        sale.setIdSale(resultSet.getLong("idSale"));
        sale.setIdToy(resultSet.getLong("idToy"));
        sale.setIdEmployee(resultSet.getLong("idEmployee"));
        return sale;
    }

    @Override
    public List<Sale> list() {
        List<Sale>salesList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM sale
                        """
            ))
        {
            while (resultSet.next()){
                Sale sale=createSale(resultSet);
                salesList.add(sale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    @Override
    public Sale byId(Long id) {
        Sale sale=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT * FROM sale WHERE id =?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                sale=createSale(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sale;
    }

    @Override
    public void save(Sale sale) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO sale(idSale,idToy,idEmployee) values (?,?,?)
                                       """)
        ){
            preparedStatement.setLong(1, sale.getIdSale());
            preparedStatement.setLong(2, sale.getIdToy());
            preparedStatement.setLong(3,sale.getIdEmployee());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Sale sale) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE sale SET idToy = ?, idClient = ?, idEmployee = ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setLong(1, sale.getIdToy());
            preparedStatement.setLong(2, sale.getIdClient());
            preparedStatement.setLong(3,sale.getIdEmployee());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM sale where id=?
                                      """)
        ){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
