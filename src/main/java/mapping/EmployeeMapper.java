package mapping;

import dtos.EmployeeDto;
import model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDto mapFrom(Employee employeeMapper){
        return new EmployeeDto(employeeMapper.getIdEmployee(),
                employeeMapper.getName(),
                employeeMapper.getEmail(),
                employeeMapper.getTelephone());
    }

    public static Employee mapFrom(EmployeeDto employeeMapper){
        return new Employee(employeeMapper.idEmployee(),
                employeeMapper.name(),
                employeeMapper.email(),
                employeeMapper.telephone());
    }


    public static List<EmployeeDto> mapFrom(List<Employee> employeeMapper){
        return employeeMapper.stream().map(EmployeeMapper::mapFrom).collect(Collectors.toList());

    }
    public static List<Employee> mapFromDto(List<EmployeeDto> employeeMapper){
        return employeeMapper.stream().map(EmployeeMapper::mapFrom).collect(Collectors.toList());
    }
}
