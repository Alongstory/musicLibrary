create table Users (
	id int primary key
);

create table Admin (
	id int primary key auto_increment,
	name varchar(200) not null,
	password varchar(2000) not null,
	unique(name)
);

create table Client (
	id int primary key auto_increment,
	name varchar(200) not null unique,  
	password varchar(2000) not null,
	unique(name)
);


create table GeneralUser (
	id int primary key
);


create table Music (
	id int primary key auto_increment,
	name varchar(200) not null,	
	singer varchar(200) not null,
	writer varchar(200) not null,
	time varchar(200),
	style enum ('Blues',
		'Jazz','Rock','Pop',
		'Classical','Country',
		'Electronic','R&B') not null,
	lyrics varchar(20000) not null,
	count int
);

create table Modifying (
	canModifies int not null,
	foreign key(canModifies)
		references Music(id)
		on update cascade
		on delete cascade,
	modifiedBy int not null,
	foreign key(modifiedBy)
		references Admin(id)
		on update cascade
		on delete cascade,
	primary key(canModifies,modifiedBy)
);

create table Recommend (
	recommend int not null,
	foreign key(recommend)
		references Client(id)
		on update cascade
		on delete cascade,
	recommendedBy int not null,
	foreign key(recommendedBy)
		references Music(id)
		on update cascade
		on delete cascade,
	primary key(recommend,recommendedBy)
);

create table searchCount (
	search int not null,
	foreign key(search)
		references Music(id)
		on update cascade
		on delete cascade,
	searchedBy int not null,
	foreign key(searchedBy)
		references Client(id)
		on update cascade
		on delete cascade,
	primary key(search,searchedBy),
	count int not null
);




