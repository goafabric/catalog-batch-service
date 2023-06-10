drop table if exists person;


create table diagnosis
(
	id varchar(36) not null
		constraint pk_diagnosis
			primary key,

	code varchar(255),
	display varchar(255),
	shortname varchar(255),

    version bigint default 0
);


create table insurance
(
	id varchar(36) not null
		constraint pk_insurance
			primary key,

	code varchar(255),
	display varchar(255),
	shortname varchar(255),

    version bigint default 0
);


create table chargeitem
(
	id varchar(36) not null
		constraint pk_chargeitem
			primary key,

	code varchar(255),
	display varchar(255),
	price decimal,

    version bigint default 0
);
