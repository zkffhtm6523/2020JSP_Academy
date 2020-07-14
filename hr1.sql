CREATE TABLE t_student(
    i_student number, --�л� ��ȣ
    nm VARCHAR2(15) not null, -- �̸� null�� �� ����
    age number(3) not null, -- ���� null�� �� ����
    primary key(i_student) -- �л� ��ȣ : primary key
);
INSERT INTO t_student (i_student,nm,age) values (1,'���ϱ�',20);

INSERT INTO t_student (i_student,nm,age) values (2,'�赵��',21);

INSERT INTO t_student (i_student,nm,age) values (3,'�����',22);

INSERT INTO t_student (i_student,nm,age) values (4,'��ÿ�',23);

INSERT INTO t_student (i_student,nm,age) values (5,'������',24);

INSERT INTO t_student (i_student,nm,age) values (6,'������',25);

INSERT INTO t_student (i_student,nm,age) values (7,'������',26);

INSERT INTO t_student (i_student,nm,age) values (8,'������',23);

INSERT INTO t_student (i_student,nm,age) values (9,'������',23);

UPDATE t_student set nm='�赵��',age=21 WHERE i_student=2;

DELETE from t_student where i_student in(1,2,3);

SELECT i_student,nm,age FROM t_student order by i_student desc; --�������� �⺻

/*������ �� �� �ִ�*/
SELECT i_student, nm , age as student_age from t_student order by i_student desc, age, nm;
-- �̹� ���̺� ������ �� age -> student_age�� �����ش�
-- orderby�� �׻� �� ��, desc �ڿ� ���� ���� ���ٸ� age�� ������������
SELECT i_student, nm , age as student_age from t_student where i_student > 3 and age > 21 order by i_student;
SELECT i_student, nm , age as student_age from t_student where i_student > 3 OR age > 21 order by i_student;

select * from t_student where nm = '������' order by i_student desc;
select * from t_student order by i_student desc, nm asc,age asc;

update t_student set nm = '���缷' where i_student = 8;
update t_student set nm = '��ȿ��' where i_student = 9;

select 2 as dd, 'haha' as nm from t_student union all
select 1 as dd, 'haha' as nm from t_student;
/*���� ���� �� �Ϸõ� ���� ��� ���� ��*/

select * from t_student where nm like '%ȿ%' order by age desc, nm desc;

select 1 from t_student;


