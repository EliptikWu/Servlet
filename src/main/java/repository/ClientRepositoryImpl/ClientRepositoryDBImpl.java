package repository.ClientRepositoryImpl;

import Config.DataBaseConnection;
import model.Client;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryDBImpl implements Repository<Client> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    private Client createClient(ResultSet resultSet) throws SQLException {
        Client client = new Client();
        client.setIdClient(resultSet.getLong("idClient"));
        client.setName(resultSet.getString("name"));
        client.setEmail(resultSet.getString("price"));
        client.setTelephone(resultSet.getInt("category"));
        Date dbSqlDate = resultSet.getDate("birthDate");
        if (dbSqlDate != null) {
            Date birthDate = dbSqlDate;
            client.setBirthDate(birthDate); //
        } else {
            client.setBirthDate(null);
        }
        return client;
    }

    @Override
    public List<Client> list() {
        List<Client>toysList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM client
                        """
            ))
        {
            while (resultSet.next()){
                Client client=createClient(resultSet);
                toysList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toysList;
    }

    @Override
    public Client byId(Long id) {
        Client client = null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT * FROM client WHERE id =?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                client = createClient(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public void save(Client client) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO client(name,email,telephone,birthDate) values (?,?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setInt(3,client.getTelephone());
            preparedStatement.setDate(4,client.getBirthDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Client client) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE client SET name = ?, price = ?, amount = ? , birthDate=? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getEmail());
            preparedStatement.setInt(3,client.getTelephone());
            preparedStatement.setDate(4,client.getBirthDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM client where id=?
                                      """)
        ){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
