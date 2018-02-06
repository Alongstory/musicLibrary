insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT '1', 'singer', 'writer', '05:02', 'Blues', 'Scjhgijhkg',0
FROM dual 
WHERE not exists (select * from Music where name = '1' and singer = 'singer');

insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT '2', 'singer2', 'writer', '04:02', 'Blues', 'Scjhgjhkg',0
FROM dual 
WHERE not exists (select * from Music where name = '2' and singer = 'singer2');


insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT '3', 'singer2', 'writer', '03:52', 'Blues', 'Scg',0
FROM dual 
WHERE not exists (select * from Music where name = '3' and singer = 'singer2');

insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT 'Black Beatles', 'Rae Sremmurd/Gucci Mane', 'A.Brown,K.Brown,M.Williams,R.Davis', '03:52', 'Blues',
'Black beatles in the city be back immediately to confiscate the moneys' ,0
FROM dual 
WHERE not exists (select * from Music where name = 'Black Beatles' and singer = 'Rae Sremmurd/Gucci Mane');


insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT 'sfg', 'singer3', 'wrtwert', '03:32', 'Rock', 'Scg',0
FROM dual 
WHERE not exists (select * from Music where name = 'sfg' and singer = 'singer3');


insert into Music(name, singer, writer, time, style, lyrics, count) 
SELECT '6', 'singer4', 'wrtwert', '03:32', 'Rock', 'Scg',0
FROM dual 
WHERE not exists (select * from Music where name = 'sfg' and singer = 'singer4');
