package repository.ToyRepositoryImpl;

import Config.DataBaseConnection;
import model.Toy;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToyRepositoryDBImpl implements Repository<Toy> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    private Toy createToy(ResultSet resultSet) throws SQLException {
        Toy toy = new Toy();
        toy.setId(resultSet.getLong("id"));
        toy.setName(resultSet.getString("name"));
        toy.setPrice(resultSet.getDouble("price"));
        toy.setCategory(resultSet.getString("category"));
        return toy;
    }

    @Override
    public List<Toy> list() {
        List<Toy>toysList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM toy
                        """
            ))
        {
            while (resultSet.next()){
                Toy toy=createToy(resultSet);
                toysList.add(toy);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toysList;
    }

    @Override
    public Toy byId(Long id) {
        Toy toy=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT * FROM toy WHERE id =?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                toy=createToy(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toy;
    }

    @Override
    public void save(Toy toy) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO toy(name,price,amount,category) values (?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            preparedStatement.setInt(3,toy.getAmount());
            preparedStatement.setString(4,toy.getCategory());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Toy toy) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE toy SET name = ?, price = ?, amount = ? , category=? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, toy.getName());
            preparedStatement.setDouble(2, toy.getPrice());
            preparedStatement.setInt(3,toy.getAmount());
            preparedStatement.setString(4,toy.getCategory());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM toy where id=?
                                      """)
        ){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}