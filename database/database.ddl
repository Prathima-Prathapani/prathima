-- change this to your team id
use MLP206;

-- comment this line for the very first time
drop table if exists EMPLOYEE;

--create database
create database mlp206;

-- create the tables

create table employee (empId  int  ,e_name varchar(20) not null,
e_des varchar(20)not null,mgr_id int ,e_add varchar(20)not null,e_phno int not null,e_mail varchar(25), DOJ date not null ,
avail_l int not null ,foreign key (mgr_id) references employee (empId),primary key(empId));

create table leev (l_id int primary key,l_type varchar(20),l_des varchar(20),
l_status varchar(20),l_startdate date,l_enddate date,le_id int not null,l_Ndays int not null,
l_Reason varchar(20),l_AppliedOn date);

create table leev_type(l_type varchar(20) not null,
                        total_leev int not null,
						avail_leev int not null,
                        empId int not null);

--alter table leev
alter table leev add constraint fk_eid foreign key(le_id) references employee(empId);
