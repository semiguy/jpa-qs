package com.rubypaper.biz.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;

public class EmployeeServiceClient {
	
	public static void main(String[] args) {
		
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
		
		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		// 엔티티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 직원 엔티티 생성
			Employee employee = new Employee();
			employee.setName("둘리");	
			
			// 트랜잭션 시작
			tx.begin();
			
			// 직원 등록 --> 관리 상태로 전환
			em.persist(employee);
			
			// 트랜잭션 종료(COMMIT)
			tx.commit();
			
			// 직원 검색
			Employee findEmp = em.find(Employee.class, 1L);
			System.out.println("검색된 직원 정보 : " + findEmp.toString());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// 트랜잭션 종료(ROLLBACK)
			tx.rollback();
		} finally {
			
			// 엔티티 매니저 및 엔티티 매니저 팩토리 종료
			em.close();
			emf.close();
		}
	}
}
