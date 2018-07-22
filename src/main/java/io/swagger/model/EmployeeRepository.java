package io.swagger.model;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  Employee Repo Interface
 *
 */


import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long>{ }


