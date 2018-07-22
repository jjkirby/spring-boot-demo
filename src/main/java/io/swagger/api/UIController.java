package io.swagger.api;

/*
 * Author: jkirby
 * Date: 7/22/18
 * Time: 11:41 AM
 * Description:  Handles the UI API Calls
 *
 */

import io.swagger.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/ui")
public class UIController  {

    private static final Logger log = LoggerFactory.getLogger(UIController.class);

    @Autowired
    EmployeeApiController employeeApiController;

    @Autowired
    private EmployeeRepository repository;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EntityManagerFactory emf;


    @RequestMapping("/employees")
    @SuppressWarnings("unchecked")

    /*
      * Handles the generic employee page requests
      *
      * @param q Is optional if the employee Id is passed
      * @param msg Handles any error messages such as the search by employee Id fails
      * @param model Thymleaf UI model

     */

    public String index(@RequestParam("q")   Optional<Long> id,
                        @RequestParam("msq") Optional<String> msg,
                        Model model) {

        log.info("Searching Employees " + id);
        
        if (id.isPresent()){
            log.debug("Id is present") ;
            Optional<Employee> e = repository.findById(id.get());
            Employee ee = e.orElse(new Employee());
            if (e.isPresent()){
                log.debug("Employee record is  present") ;
            } else{
                log.debug("Employee record is NOT  present") ;
                return "redirect:/ui/employees?msg=NOT_FOUND";
            }
            model.addAttribute("employee", repository.findById(id.get()));
            if(ee.getStatus().equalsIgnoreCase("ACTIVE")) {
                return "redirect:/ui/edit/" + id.get();
            }else{
                return "redirect:/ui/view/" + id.get();
            }
        } else{
            log.debug("Id is NOT present") ;
            ResponseEntity employee =  employeeApiController.searchAllEmployees();
            List<Employee> employees = (List<Employee>) employee.getBody();
            model.addAttribute("employees", employees);
            return "employees";
        }
    }

    /*
     * Handles the edit employee requests
     *
     * @param employeeId  Employee Id
     * @param model Thymleaf UI model
     */
    @RequestMapping(value = "/edit/{employeeId}")

    public String editEmployee(@PathVariable("employeeId") Long employeeId, Model model){

        model.addAttribute("employee", repository.findById(employeeId));

        return "editEmployee";
    }

    /*
     * Handles the view only employee requests
     *
     * @param employeeId  Employee Id
     * @param model Thymleaf UI model
     */

    @RequestMapping(value = "/view/{employeeId}")

    public String viewEmployee(@PathVariable("employeeId") Long employeeId, Model model){

        model.addAttribute("employee", repository.findById(employeeId));

        return "viewEmployee";
    }

    /*
     * Handles the delete employee requests.  This can only be done by a user of role of Admin.
     * The record is not deleted but marked INACTIVE
     *
     * @param employeeId  Employee Id
     * @param model Thymleaf UI model
     */
    @RequestMapping(value = "/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Long employeeId, Model model) {

       JPADeleteEmployee(employeeId);

        return "redirect:/ui/employees";
        
    }

    /*
     * Performs JPA call to change record status to INACTIVE
     *
     * @param employeeId  Employee Id
    */
    private void JPADeleteEmployee(Long employeeId) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Employee employee = em.find(Employee.class, employeeId);

        tx.begin();
        em.createNativeQuery("update employee e set e.status = 'INACTIVE' where e.id =" + employeeId).executeUpdate();
        tx.commit();
        em.close();

    }

    /*
     * Adds a new Employee
     *
    * @param model Thymleaf UI model
    */
    @RequestMapping(value = "/add")

    public String addEmployee( Model model){
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }


    /*
     * Saves an Employee Record
     *
     * @param employee  Employee POJO
    */
    @RequestMapping(value = "save", method = RequestMethod.POST)

    public String save(Employee employee){
        employee.setStatus("ACTIVE");
        repository.save(employee);
        return "redirect:/ui/employees";
    }


}
