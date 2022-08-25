package com.rubypaper.biz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DEPT_ID")
	private Department dept;
	
	// 양방향 참조 메소드
	public void setDept(Department department) {
		
		this.dept = department;
		// Department 엔티티의 컬렉션에도 Employee 참조를 설정 한다.
		department.getEmployeeList().add(this);
	}
}
