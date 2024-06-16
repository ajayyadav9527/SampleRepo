package com.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dep_id")
    private Long depid;

    @Column(name = "depname")
    private String depname;

    @Column(name = "location")
    private String location;

    // One-to-many relationship with Employee
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<Employee>();

    public Department() {
        // Default constructor required by JPA
    }

    public Department(String depname, String location) {
        this.depname = depname;
        this.location = location;
    }

    public Long getDepId() {
        return depid;
    }

    public void setDepId(Long depId) {
        this.depid = depId;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "depId=" + depid +
                ", depname='" + depname + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + employees +
                '}';
    }
}
