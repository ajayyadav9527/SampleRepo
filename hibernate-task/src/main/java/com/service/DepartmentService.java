package com.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.query.Query;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import com.config.Configurations;
import com.entity.Department;
import com.entity.Employee;

public class DepartmentService {

	public static void createDepartment(String depname, String location) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Department department = new Department();
			department.setDepname(depname);
			department.setLocation(location);

			session.save(department);

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

	public static Long getCountByDistDepname() {
		Session session = Configurations.getSessionFactory().openSession();
		Long distDepname = 0L;

		try {
			Criteria criteria = session.createCriteria(Department.class);
			criteria.setProjection(Projections.countDistinct("depname"));
			distDepname = (Long) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return distDepname;
	}

	@SuppressWarnings("unchecked")
	public static void fetchDepartmentsWithEmployees() {
		Session session = Configurations.getSessionFactory().openSession();

		try {
			// Create Criteria for Department entity
			Criteria criteria = session.createCriteria(Department.class);

			// Create alias for inner join with employees
			criteria.createAlias("employees", "e", CriteriaSpecification.INNER_JOIN);

			// Execute query and get list of Department entities
			List<Department> departments = criteria.list();

			// Print the departments and their employees
			for (Department department : departments) {
				System.out.println("Department: " + department.getDepname());
				Set<Employee> employees = department.getEmployees();
				for (Employee employee : employees) {
					System.out.println("  Employee: " + employee.getFirstname() + " " + employee.getLastname());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void deleteEmployeeFromDepartment(Long employeeId) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, employeeId);
			if (employee == null) {
				throw new IllegalArgumentException("Employee with id " + employeeId + " not found");
			}

			Department department = employee.getDepartment();
			if (department != null) {
				department.getEmployees().remove(employee);
			}

			session.delete(employee);

			transaction.commit();
			System.out.println("Employee deleted successfully");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void updateDepartment(Long employeeId, Long departmentId) {
		Session session = Configurations.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Employee employee = session.get(Employee.class, employeeId);
			if (employee == null) {
				throw new IllegalArgumentException("Employee with id " + employeeId + " not found");
			}

			Department department = session.get(Department.class, departmentId);
			if (department == null) {
				throw new IllegalArgumentException("Department with id " + departmentId + " not found");
			}

			employee.setDepartment(department);

			session.update(employee);

			transaction.commit();
			System.out.println("Employee department updated successfully");
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
//	public static void fetchEmployeesWithMultipleEmployeesInDepartment(int countThreshold) {
//		Session session = Configurations.getSessionFactory().openSession();
//
//        Transaction transaction = null;
//
//        try {
//            transaction = session.beginTransaction();
//            
//
//            Criteria criteria = session.createCriteria(Department.class, "d");
//            criteria.createAlias("d.employees", "e", JoinType.LEFT_OUTER_JOIN);
//
//            ProjectionList projections = Projections.projectionList();
//            projections.add(Projections.property("d.depid"), "depid");
//            projections.add(Projections.property("d.depname"), "depname");
//            projections.add(Projections.count("e.id"), "employeeCount");
//
//            criteria.setProjection(projections);
//            criteria.add(Restrictions.gt(Projections.count("e.id"), countThreshold));
//            criteria.setResultTransformer(Transformers.aliasToBean(Department.class));
//
//            List<Department> departments = criteria.list();
//
//            for (Department department : departments) {
//                System.out.println("Department ID: " + department.getDepId());
//                System.out.println("Department Name: " + department.getDepname());
//                System.out.println("Employee Count: " + department.getEmployees().size()); // This will give the count of employees in memory
//                System.out.println();
//            }
//       
//       
//       
//        }catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
}
