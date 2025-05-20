package com.itp.FirstSpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itp.FirstSpringBoot.model.Employee;
import com.itp.FirstSpringBoot.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(int id) {
		Employee employee = employeeRepository.findById(id).get();
//		if (employee.isPresent()) {
//			return employee;
//		} else {
//			throw new EmployeeNotFoundException("Employee not found with id " + id);
//		}
		return employee;
	}

	public List<Employee> listEmployee() {
		return employeeRepository.findAll();
	}

	public Page<Employee> employeeByPage(int pageNumber, int pageSize,String sortField, String sortDirection) {
		Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection.toUpperCase()),sortField);
        return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
	}

	public Employee updateEmployee(int id, Employee employee) {
		Employee dbEmployee=employeeRepository.findById(id).get();
		dbEmployee.setName(employee.getName());
		dbEmployee.setSalary(employee.getSalary());
		return employeeRepository.save(dbEmployee);
		}

	public void deleteProduct(int id) {
		employeeRepository.deleteById(id);
		
	}
}
