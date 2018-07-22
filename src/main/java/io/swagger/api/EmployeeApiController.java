package io.swagger.api;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  Handles API Controller
 *
 */

import io.swagger.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import io.swagger.model.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/api")
public class EmployeeApiController implements EmployeeApi {

    private static final Logger log = LoggerFactory.getLogger(EmployeeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private EmployeeRepository repository;

    @org.springframework.beans.factory.annotation.Autowired
    public EmployeeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addEmployee(@ApiParam(value = "New employee to add"  )  @Valid @RequestBody Employee employee) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteEmployee(@ApiParam(value = "Employee id to delete",required=true) @PathVariable("employeeId") String employeeId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<String> getEmployeeId(@ApiParam(value = "pass an optional employee ID string for looking up employee",required=true) @PathVariable("employeeId") String employeeId) {

        try {

            Optional<Employee> oe = repository.findById(Long.parseLong(employeeId));

            Employee employee = oe.orElse(new Employee());

            ObjectMapper mapper = new ObjectMapper();
            String employeeString = mapper.writeValueAsString(employee);
            log.info("Employee: " + employeeString) ;

            return new ResponseEntity<>(employeeString, HttpStatus.OK);
        }catch(Exception e) {
            log.error("Couldn't find Employee", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<List<Employee>> searchAllEmployees() {
        try {
            List<Employee> employees = (List<Employee>) repository.findAll();
            return new ResponseEntity<>(employees,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Couldn't find ANY Employees", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> updateEmployee(@ApiParam(value = "employee that needs to be updated",required=true) @PathVariable("employeeId") String employeeId,@ApiParam(value = "Updated employee object" ,required=true )  @Valid @RequestBody Employee body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
