package com.dto;

public class EmployeeDTO {

    private String firstname;
    private String lastname;
    private String email;
    private Double salary;
    private Long departmentId; // Assuming departmentId is used to link with Department entity

    // Constructors
    public EmployeeDTO() {
    }

    public EmployeeDTO(String firstname, String lastname, String email, Double salary, Long departmentId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public EmployeeDTO(String firstname, String lastname, String email, Double salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.salary = salary;
	}

	// Getters and Setters
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    // toString() method
    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }
}
