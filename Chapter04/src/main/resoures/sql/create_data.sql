-- ���̺� ����
DROP TABLE s_dept;
DROP TABLE s_emp;

-- �μ� ���̺�
CREATE TABLE s_dept(
  dept_id NUMBER(7) CONSTRAINT s_dept_id_nn NOT NULL, -- �μ� ���̵�
  name VARCHAR2(25) CONSTRAINT s_dept_name_nn NOT NULL, -- �μ� �̸�
  CONSTRAINT s_dept_id_pk PRIMARY KEY (dept_id)
);

-- ���� ���̺�
CREATE TABLE s_emp(
  id NUMBER(7) CONSTRAINT s_emp_id_nn NOT NULL, -- ���� ���̵�
  name VARCHAR2(25) CONSTRAINT s_emp_name_nn NOT NULL, -- ���� �̸�
  dept_id NUMBER(7), -- �μ� ���̵�
  CONSTRAINT s_emp_id_pk PRIMARY KEY(id)
);

ALTER TABLE s_emp ADD CONSTRAINT s_emp_dept_id_fk FOREIGN KEY(dept_id) REFERENCES s_dept (dept_id);

INSERT INTO s_dept VALUES(1, '���ߺ�');
INSERT INTO s_emp VALUES(1, '�Ѹ�', 1);
INSERT INTO s_emp VALUES(2, '�����', 1);