
package io.swagger.api;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:   Interface for Employee API  Calls
 *
 */

import io.swagger.model.Employee;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;

@Api(value = "employee", description = "the employee API")
public interface EmployeeApi {

    @ApiOperation(value = "adds an employee", nickname = "addEmployee", notes = "Adds an employee to the system", tags={ "Employee APIs", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "employee record created"),
        @ApiResponse(code = 400, message = "invalid input, object invalid"),
        @ApiResponse(code = 409, message = "an existing employee already exists") })
    @RequestMapping(value = "/employee",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addEmployee(@ApiParam(value = "New employee to add"  )  @Valid @RequestBody Employee employee);


    @ApiOperation(value = "deletes an employee", nickname = "deleteEmployee", notes = "", tags={ "Employee APIs", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid Employee ID supplied"),
        @ApiResponse(code = 404, message = "Employee not found") })
    @RequestMapping(value = "/employee/{employeeId}",
        produces = { "application/json", "application/xml" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteEmployee(@ApiParam(value = "Employee id to delete",required=true) @PathVariable("employeeId") String employeeId);


    @ApiOperation(value = "fetches by employee id", nickname = "getEmployeeId", notes = "By passing in theEmployee Id, you can fetch the specific employee record", response = String.class, tags={ "Employee APIs", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = String.class),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/employee/{employeeId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<String> getEmployeeId(@ApiParam(value = "pass an optional employee ID string for looking up employee",required=true) @PathVariable("employeeId") String employeeId);


    @ApiOperation(value = "fetches all employees", nickname = "searchAllEmployees", notes = "Fetches all available employees in the system ", response = Employee.class, responseContainer = "List", tags={ "Employee APIs", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = Employee.class, responseContainer = "List") })
    @RequestMapping(value = "/employee",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Employee>> searchAllEmployees();


    @ApiOperation(value = "updates an employee", nickname = "updateEmployee", notes = "This can only be done by the logged in user.", tags={ "Employee APIs", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "invalid employee id supplied"),
        @ApiResponse(code = 404, message = "employee not found") })
    @RequestMapping(value = "/employee/{employeeId}",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateEmployee(@ApiParam(value = "employee that needs to be updated",required=true) @PathVariable("employeeId") String employeeId,@ApiParam(value = "Updated employee object" ,required=true )  @Valid @RequestBody Employee body);

}
