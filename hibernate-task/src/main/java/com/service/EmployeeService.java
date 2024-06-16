package com.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import com.config.Configurations;
import com.dto.EmployeeDTO;
import com.entity.Department;
import com.entity.Employee;

public class EmployeeService {

	public static void createEmployee(String firstname, String lastname, String email, Double salary, Long dep_id) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = new Employee();
			employee.setFirstname(firstname);
			employee.setLastname(lastname);
			employee.setEmail(email);
			employee.setSalary(salary);
			Department department = session.get(Department.class, dep_id);
			employee.setDepartment(department);

			session.save(employee);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static Employee getEmployee(Long id) {
		Session session = Configurations.getSessionFactory().openSession();
		Employee employee = null;

		try {
			employee = session.get(Employee.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employee;
	}

	public static void updateEmployee(Long id, String firstname, String lastname, String email, Double salary) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, id);
			if (employee != null) {
				employee.setFirstname(firstname);
				employee.setLastname(lastname);
				employee.setEmail(email);
				employee.setSalary(salary);

				session.update(employee);
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void deleteEmployee(Long id) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, id);
			if (employee != null) {
				session.delete(employee);
				transaction.commit();
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Employee> getAllEmployees() {
		Session session = Configurations.getSessionFactory().openSession();
		List<Employee> employees = null;

		try {
			employees = session.createQuery("FROM Employee", Employee.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static List<Employee> getEmployeesWithSalaryGreaterThan(double salary) {
		Session session = Configurations.getSessionFactory().openSession();
		List<Employee> employees = null;

		try {
			Query<Employee> query = session.createQuery("FROM Employee e WHERE e.salary > :salary", Employee.class);
			query.setParameter("salary", salary);
			employees = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static List<Employee> getEmpOrderByLastname() {
		Session session = Configurations.getSessionFactory().openSession();
		List<Employee> employees = null;

		try {
			Query<Employee> query = session.createQuery("FROM Employee ORDER BY lastname", Employee.class);
			employees = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static List<Employee> getEmpByFirstnameLastname(String firstname, String lastname) {
		Session session = Configurations.getSessionFactory().openSession();
		List<Employee> employees = null;

		try {
			Query<Employee> query = session
					.createQuery("FROM Employee WHERE firstname = :firstname AND lastname = :lastname", Employee.class);
			query.setParameter("firstname", firstname);
			query.setParameter("lastname", lastname);
			employees = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static Long getEmpCount() {
		Session session = Configurations.getSessionFactory().openSession();
		Long count = 0L;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.count("id"));
			count = (Long) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return count;
	}

	public static Double getEmpMaxSal() {
		Session session = Configurations.getSessionFactory().openSession();
		Double maxSalary = 0.0;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.max("salary"));
			maxSalary = (Double) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return maxSalary;
	}

	public static Double getAvgSal() {
		Session session = Configurations.getSessionFactory().openSession();
		Double avgSalary = 0.0;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.avg("salary"));
			avgSalary = (Double) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return avgSalary;
	}

	public static List<Object[]> getEmpfirstAndLastname() {
		Session session = Configurations.getSessionFactory().openSession();
		List<Object[]> employees = null;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("firstname"), "firstname")
					.add(Projections.property("lastname"), "lastname"));

			employees = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static void createEmployeeByDTO(EmployeeDTO employeeDTO) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = new Employee();
			employee.setFirstname(employeeDTO.getFirstname());
			employee.setLastname(employeeDTO.getLastname());
			employee.setEmail(employeeDTO.getEmail());
			employee.setSalary(employeeDTO.getSalary());
			Department department = session.get(Department.class, employeeDTO.getDepartmentId());
			employee.setDepartment(department);

			session.save(employee);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static List<Object[]> getEmpIdAndFirstName() {
		Session session = Configurations.getSessionFactory().openSession();
		List<Object[]> employees = null;

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("id"), "id")
					.add(Projections.property("firstname"), "firstname"));

			employees = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public static Long getEmpGroupByDep() {
		Session session = Configurations.getSessionFactory().openSession();
		Long empconutBydep = 0L;
		try {
			Criteria cr = session.createCriteria(Employee.class);

			Projection p = Projections.groupProperty("department");
			Projection p1 = Projections.count("id");

			ProjectionList plist = Projections.projectionList();
			plist.add(p);
			plist.add(p1);

			cr.setProjection(plist);

			List<Object[]> w = cr.list();

			for (Object[] z : w) {
				System.out.println(z[1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return empconutBydep;
	}

	public static void fetchAllEmployeesWithDepartments() {
		Session session = Configurations.getSessionFactory().openSession();

		try {
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.setFetchMode("department", FetchMode.JOIN);

			List<Employee> employees = criteria.list();

			for (Employee employee : employees) {

				System.out.println(employee);
				System.out.println(employee.getDepartment());

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@SuppressWarnings("deprecation")
	public static void fetchAllDepartmentAndMatchingEmployees() {
		Session session = Configurations.getSessionFactory().openSession();
		try {
			// Create Criteria for Department entity
			Criteria criteria = session.createCriteria(Department.class);

			// Set FetchMode to JOIN for employees to perform a left join
			criteria.setFetchMode("employees", FetchMode.JOIN);
			criteria.createAlias("employees", "e", CriteriaSpecification.LEFT_JOIN);

			// Execute query and get list of Department entities
			List<Department> departments = criteria.list();

			System.out.println(departments);

			// Iterate through the results and print the departments and their employees
//           for (Department department : departments) {
//               System.out.println("Department: " + department);
//               if (department.getEmployees() != null && !department.getEmployees().isEmpty()) {
//                   for (Employee employee : department.getEmployees()) {
//                       System.out.println("  Employee: " + employee);
//                   }
//               } else {
//                   System.out.println("  No employees");
//               }
//           }
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static void joinEmployeeAndDepartment() {
		Session session = Configurations.getSessionFactory().openSession();

		try {
			// HQL query to join Employee and Department
			String hql = "SELECT e, d FROM Employee e JOIN e.department d";

			// Execute the query and get results
			List<Object[]> resultList = session.createQuery(hql).list();

			// Print the results
			for (Object[] result : resultList) {
				Employee employee = (Employee) result[0];
				Department department = (Department) result[1];
				System.out.println("Employee: " + employee.getFirstname() + " " + employee.getLastname()
						+ ", Department: " + department.getDepname());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static void fetchEmployeesByDepartmentLocation() {
		Session session = Configurations.getSessionFactory().openSession();
		try {
			// HQL query to join Employee and Department
			String hql = "SELECT e, d FROM Employee e JOIN e.department d WHERE d.location='3 Rd Floor'";

			// Execute the query and get results
			List<Object[]> resultList = session.createQuery(hql).list();

			// Check if resultList is empty
			if (resultList.isEmpty()) {
				System.out.println("No employees found for the given department location.");
			} else {
				// Print the results
				for (Object[] result : resultList) {
					Employee employee = (Employee) result[0];
					Department department = (Department) result[1];
					System.out.println("Employee: " + employee.getFirstname() + " " + employee.getLastname()
							+ ", Department: " + department.getDepname());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

//    @SuppressWarnings("deprecation")
//    public static void CountMatchingEmployeesQuery() {
//    	Session session = Configurations.getSessionFactory().openSession();
//        try {
//            // Create HQL query to count employees per department
//            String hql = "SELECT * FROM employee,department";
//            
//            String hql1 = "SELECT * FROM employee where employee.id=1 ORDER BY firstname";
//            
//            // Execute query and get results
//            List resultList = session.createQuery(hql1).getResultList();
//            
//            System.out.println(resultList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
	public static void createEmployeeWithExistingDep(EmployeeDTO employeeDTO, Long dep_id) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = new Employee();
			employee.setFirstname(employeeDTO.getFirstname());
			employee.setLastname(employeeDTO.getLastname());
			employee.setEmail(employeeDTO.getEmail());
			employee.setSalary(employeeDTO.getSalary());
			Department department = session.get(Department.class, dep_id);
			employee.setDepartment(department);

			session.save(employee);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}


}