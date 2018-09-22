create table user_health
(userid varchar(50) not null,
profile varchar(50) not null,
healthmeter decimal(5,2),
submission_date DATE,
unique key uk_1 (userid, submission_date)
);

insert into user_health values('test@gmail.com','admin',1, sysdate);