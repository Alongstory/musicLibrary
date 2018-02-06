INSERT INTO Music(name, years, lyrics) 
SELECT '2', '123' ,'sdfg'
FROM dual 
WHERE not exists (select * from Music m, MusicSinger ms
where m.name = '2' and ms.singer = 'singer' and m.id = ms.music);

INSERT INTO MusicSinger(music, singer)
SELECT id,'singer'
  from Music
 where name = '2';

INSERT INTO MusicWriter(music, writer)
SELECT id,'writer'
  from Music
 where name = '2';

INSERT INTO MusicStyle(music, style)
SELECT id,'Blues'
  from Music
 where name = '2';

