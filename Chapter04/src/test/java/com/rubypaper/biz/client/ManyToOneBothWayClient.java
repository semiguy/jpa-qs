package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;

public class ManyToOneBothWayClient {
	
	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		try {
			//dataInsert(emf);
			dataInsert2(emf);
			dataSelect(emf);
			//dataUpdate(emf);
			//dataDelete(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}
	
	private static void dataInsert(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 부서 등록
		Department department = new Department();
		department.setName("개발부");
		em.persist(department);
		
		// 직원 등록
		Employee employee1 = new Employee();
		employee1.setName("둘리");
		employee1.setDept(department);
		em.persist(employee1);
		
		// 직원등록
		Employee employee2 = new Employee();
		employee2.setName("도우너");
		employee2.setDept(department);
		em.persist(employee2);
		
		// Department.employeeList에 Employee 등록
		//department.getEmployeeList().add(employee1);
		//department.getEmployeeList().add(employee2);
		
		System.out.println(department.getName() + "의 직원 수 : " + department.getEmployeeList().size());
		
		em.getTransaction().commit();
		em.close();
	}
	
	private static void dataInsert2(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 직원1 등록
		Employee employee1 = new Employee();
		employee1.setName("둘리");
		em.persist(employee1);
		
		// 직원2 등록
		Employee employee2 = new Employee();
		employee2.setName("도우너");
		em.persist(employee2);
		
		// 부서 등록
		Department department = new Department();
		department.setName("개발부");
		department.getEmployeeList().add(employee1);
		department.getEmployeeList().add(employee1);
		em.persist(department);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	private static void dataInsert3(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 부서 등록
		Department department = new Department();
		department.setName("개발부");
		//em.persist(department);
		
		// 직원 여러명 등록
		for(int i = 1; i <= 5; i++) {
			
			Employee employee = new Employee();
			employee.setName("직원-" + i);
			employee.setDept(department);
			//em.persist(employee);
		}
		em.persist(department); 
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	
	private static void dataSelect(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		Department department = em.find(Department.class, 1L);
		
		System.out.println("검색된 부서 : " + department.getName());
		System.out.println("부서에 소속된 직원 명단");
		
		for(Employee employee : department.getEmployeeList()) {
			System.out.println(employee.getName() + "(" + employee.getDept().getName() + ")");
		}
	}
	
	private static void dataUpdate(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 신규 부서 등록
		Department department = new Department();
		department.setName("영업부");
		em.persist(department);
		
		// 부서 변경
		Employee employee = em.find(Employee.class, 1L);
		employee.setDept(department);
		em.getTransaction().commit();
	}
	
	private static void dataDelete(EntityManagerFactory emf) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// 부서에 대한 참조 제거
		Employee employee1 = em.find(Employee.class, 1L);
		employee1.setDept(null);
		Employee employee2 = em.find(Employee.class, 2L);
		employee2.setDept(null);
		
		Department department = em.find(Department.class, 1L);
		em.remove(department);
		em.getTransaction().commit();
	}
}
