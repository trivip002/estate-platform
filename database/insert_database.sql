set search_path to estate_platform;

insert into role(code,name) values('ADMIN','Quản trị hệ thống');
insert into role(code,name) values('USER','người dùng');

insert into users(username,password,fullname,status,phonenumber)
values('manager','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','admin',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvand','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van d',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvane','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van e',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvanf','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van f',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvang','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van g',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvanh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van h',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvanj','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van j',1,01882841267);
insert into users(username,password,fullname,status,phonenumber)
values('nguyenvank','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van k',1,01882841267);




INSERT INTO user_role(user_id,role_id) VALUES (1,1);
INSERT INTO user_role(user_id,role_id) VALUES (2,2);
INSERT INTO user_role(user_id,role_id) VALUES (3,2);
INSERT INTO user_role(user_id,role_id) VALUES (4,2);
INSERT INTO user_role(user_id,role_id) VALUES (5,2);
INSERT INTO user_role(user_id,role_id) VALUES (6,2);
INSERT INTO user_role(user_id,role_id) VALUES (7,2);
INSERT INTO user_role(user_id,role_id) VALUES (8,2);
INSERT INTO user_role(user_id,role_id) VALUES (9,2);
INSERT INTO user_role(user_id,role_id) VALUES (10,2);


insert into building(name ,district ,precint, basementNumber, acreageFloor, acreageRent, rentCost, serviceCharge, carCharge, overtimeCharge, electricBill, deposit, pay, agencyCharge,priority)
values ('Tòa nhà 1','Tân bình' , 'Phường 5', '2' , '5.15', '7', '3000000', '500000','400000','300000','250000','450000','1500000','570000',0);
insert into building(name ,district ,precint, basementNumber, acreageFloor, acreageRent, rentCost, serviceCharge, carCharge, overtimeCharge, electricBill, deposit, pay, agencyCharge,priority)
values ('Tòa nhà 2', 'Gò Vấp', 'Phường 8', '2' , '5.15', '7', '3000000', '500000','400000','300000','250000','450000','1500000','570000',0);

INSERT INTO assignment(staff_id, building_id) VALUES (1,1);
INSERT INTO assignment(staff_id, building_id) VALUES (1,2);

INSERT INTO priority(staff_id, building_id) VALUES (1,1);

insert into customer(name , phonenumber, email, status)
values('Huynh Minh Tri','0938667013','trivip002@gmail.com',0);

insert into user_customer(user_id, customer_id) values (1,1);

