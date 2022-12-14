package com.rubypaper.biz.client;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.rubypaper.biz.domain.Employee;

public class EmployeeServiceClient {
	
	public static void main(String[] args) {
		
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
		
		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		
		// 엔티티 트랜잭션 생성
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 직원 엔티티 생성
			Employee employee = new Employee();
			employee.setId(1L);
			employee.setName("둘리");
			employee.setMailId("gurum");
			employee.setStartDate(new Date());
			employee.setTitle("과장");
			employee.setDeptName("총무부");
			employee.setSalary(2500.00);
			employee.setCommissionPct(12.50);
			
			// 트랜잭션 시작
			tx.begin();
			
			// 직원 등록 처리
			em.persist(employee);
			
			// 트랜잭션 종료
			tx.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			// 트랜잭션 종료
			tx.rollback();
		} finally {
			// 엔티티 매니저 및 엔티티 매니저 팩토리 종료
			em.close();
			emf.close();
		}
	}
}
