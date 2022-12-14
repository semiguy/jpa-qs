package com.rubypaper.biz.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPT_ID")
	private Long deptId;
	
	@Column(length = 25, nullable = false)
	private String name;
	
	// 양방향 연관 관계 매핑 설정
	//@OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
	//private List<Employee> employeeList = new ArrayList<Employee>();
	//@OneToMany(mappedBy = "dept")
	@OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
	private List<Employee> employeeList = new ArrayList<Employee>();
	//private Set<Employee> employeeList = new HashSet<Employee>();
}
