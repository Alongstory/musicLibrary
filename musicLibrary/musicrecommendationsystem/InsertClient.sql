#insert into Client(name, password) values('b', 'aaaaaaaaaa')


INSERT INTO client(name, password) 
SELECT 'a', '123' 
FROM dual 
WHERE not exists (select * from client 
where name = 'a');


INSERT INTO client(name, password) 
SELECT 'b', '123' 
FROM dual 
WHERE not exists (select * from client 
where name = 'b');

INSERT INTO Admin(name, password) 
SELECT 'a', '123' 
FROM dual 
WHERE not exists (select * from client 
where name = 'a');