insert into role values(1, 'USER');
insert into role values (2, 'ADMIN');

select * from role;

insert into user values( 1, 'user@test.org' , TRUE, 'User', 'Username', 'password' , 'user');

insert into user values( 2, 'admin@test.org' , TRUE, 'Admin', 'Adminname', 'password' , 'admin');

select * from user;


insert into user_roles values(1, 1);
insert into user_roles values(2, 1);
insert into user_roles values(2, 2);