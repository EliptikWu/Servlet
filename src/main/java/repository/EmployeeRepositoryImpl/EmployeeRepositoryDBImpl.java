package repository.EmployeeRepositoryImpl;

import Config.DataBaseConnection;
import model.Employee;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryDBImpl implements Repository<Employee> {
    private Connection getConnection() throws SQLException {
        return DataBaseConnection.getInstance();
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setIdEmployee(resultSet.getLong("idEmployee"));
        employee.setName(resultSet.getString("name"));
        employee.setEmail(resultSet.getString("email"));
        employee.setTelephone(resultSet.getInt("telephone"));
        return employee;
    }

    @Override
    public List<Employee> list() {
        List<Employee>employeesList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT * FROM employee
                        """
            ))
        {
            while (resultSet.next()){
                Employee employee=createEmployee(resultSet);
                employeesList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeesList;
    }

    @Override
    public Employee byId(Long id) {
        Employee employee=null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT * FROM employee WHERE id =?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                employee=createEmployee(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void save(Employee employee) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                       INSERT INTO employee(name,email,telephone) values (?,?,?)
                                       """)
        ){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3,employee.getTelephone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE employee SET name = ?, email = ?, telephone = ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3,employee.getTelephone());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM employee where id=?
                                      """)
        ){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
