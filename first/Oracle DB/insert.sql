insert into t_student (i_student,nm,age) values(10,'dddd',23);
-- ���� ������ ���̺��� �÷� ������ ���� �ʿ䰡 ����. ���� �÷� Ÿ���� not null�̶�� ������ �����ϴ�
insert into t_student values (10,'������',27);
-- ���̺�� �ڿ� �÷����� ���������ϳ� �Ǽ� �����ϴ°�..
-- insert into t_student values (10,'������',27),(11,'������',27),(12,'������',27); DB������ �����ϳ�
-- Java���� �������� ���� ���� FM��� �ϰ� ������� ����..

INSERT INTO t_student (i_student,nm,age) select 15,first_name,employee_id from employees
where employee_id = 100;

INSERT INTO t_student (i_student, nm, age) select 13, nm, age from t_student where i_student = 12;

INSERT INTO t_student (i_student, nm, age) SELECT 12, '�������',28 from dual;