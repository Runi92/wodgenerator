create table if not exists wodgenerator.measurementunits
(
	id serial not null
		constraint measurementunits_pk
			primary key,
	name varchar not null
);

comment on table wodgenerator.measurementunits is 'Таблица для хранения единиц измерения';

comment on column wodgenerator.measurementunits.id is 'Идентификатор';

comment on column wodgenerator.measurementunits.name is 'Название единицы измерения';

alter table wodgenerator.measurementunits owner to postgres;

create unique index if not exists measurementunits_id_uindex
	on wodgenerator.measurementunits (id);

create unique index if not exists measurementunits_name_uindex
	on wodgenerator.measurementunits (name);

