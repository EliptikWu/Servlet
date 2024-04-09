package repository.EmployeeRepositoryImpl;

import dtos.EmployeeDto;
import mapping.EmployeeMapper;
import model.Employee;
import repository.Repository;

import java.util.List;

public class EmployeeRepositoryFilesImpl implements Repository<EmployeeDto>{
    private final Repository<Employee> Repository;

    public EmployeeRepositoryFilesImpl() {
        this.Repository = new EmployeeRepositoryDBImpl();
    }

    @Override
    public List<EmployeeDto> list() {
        System.out.println("listando desde archivos");
        return Repository.list().stream().map(EmployeeMapper::mapFrom).toList();
    }

    @Override
    public EmployeeDto byId(Long id) {
        Employee employee = Repository.byId(id);
        return EmployeeMapper.mapFrom(employee);
    }

    @Override
    public void save(EmployeeDto EmployeeDto) {
        System.out.println("Estoy llamando implementacion de archivos");
    }

    @Override
    public void delete(Long id) {
        Repository.delete(id);
    }

    @Override
    public void update(EmployeeDto EmployeeDto) {

    }
}
