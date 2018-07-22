package io.swagger.model;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.Valid;
import javax.validation.constraints.*;


/**
 * Employee DAO
 */
@Validated
@Entity
public class Employee   {

  private static final Logger log = LoggerFactory.getLogger(Employee.class);

  @JsonProperty("id")
  private long id;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("middleInitial")
  private String middleInitial = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("dateOfBirth")
  @DateTimeFormat(pattern = "yyyy-mm-dd")

  private String dateOfBirth = null;

  @JsonProperty("dateOfEmployment")
  @DateTimeFormat(pattern = "yyyy-mm-dd")

  private String dateOfEmployment = null;

  @JsonProperty("status")
  private String status = null;

  public Employee id(long id) {
    this.id = id;
    return this;
  }
  public Employee(){}

 public Employee(String firstName, String lastName, String middleInitial,
                        String status, String dateOfBirth, String dateOfEmployment) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleInitial = middleInitial;
    this.status = status;
    this.dateOfBirth = dateOfBirth;
    this.dateOfEmployment = dateOfEmployment;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "0", value = "")

  @Valid
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Employee firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
  **/
  @ApiModelProperty(example = "John", required = true, value = "")
  @NotNull


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Employee middleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
    return this;
  }

  /**
   * Get middleInitial
   * @return middleInitial
  **/
  @ApiModelProperty(value = "")

@Size(max=1) 
  public String getMiddleInitial() {
    return middleInitial;
  }

  public void setMiddleInitial(String middleInitial) {
    this.middleInitial = middleInitial;
  }

  public Employee lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
  **/
  @ApiModelProperty(example = "Smith", required = true, value = "")
  @NotNull
  
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Employee dateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Get dateOfBirth
   * @return dateOfBirth
  **/
  @ApiModelProperty(example = "1985-06-21T00:00:00.000Z", required = true, value = "")
  @NotNull

  @Valid
  public String getDateOfBirth() {
        return dateOfBirth;
      }

  public void setDateOfBirth(String dateOfBirth) {
     this.dateOfBirth = dateOfBirth;

  }

  /**
   * Get dateOfEmployment
   * @return dateOfEmployment
  **/
  @ApiModelProperty(example = "2016-06-23T00:00:00.000Z", required = true, value = "")
  @NotNull
  @Valid
  public String getDateOfEmployment() {
        return dateOfEmployment;
      }


  public void setDateOfEmployment(String dateOfEmployment) {
       log.info("Here DOE " + dateOfEmployment);
       log.info("Class for DOE  " + dateOfEmployment.getClass().getName());

       this.dateOfEmployment = dateOfEmployment;
   }

  public Employee status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "ACTIVE", value = "")

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id) &&
        Objects.equals(this.firstName, employee.firstName) &&
        Objects.equals(this.middleInitial, employee.middleInitial) &&
        Objects.equals(this.lastName, employee.lastName) &&
        Objects.equals(this.dateOfBirth, employee.dateOfBirth) &&
        Objects.equals(this.dateOfEmployment, employee.dateOfEmployment) &&
        Objects.equals(this.status, employee.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleInitial, lastName, dateOfBirth, dateOfEmployment, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employee {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    middleInitial: ").append(toIndentedString(middleInitial)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
    sb.append("    dateOfEmployment: ").append(toIndentedString(dateOfEmployment)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

