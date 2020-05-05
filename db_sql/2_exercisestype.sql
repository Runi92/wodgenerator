create table if not exists wodgenerator.exercisetype
(
	id serial not null
		constraint exercisetype_pk
			primary key,
	name varchar not null
);

comment on table wodgenerator.exercisetype is 'Таблица для хранения типа упражнения';

comment on column wodgenerator.exercisetype.id is 'Идентификатор
';

comment on column wodgenerator.exercisetype.name is 'Название типа упражнения';

alter table wodgenerator.exercisetype owner to postgres;

create unique index if not exists exercisetype_id_uindex
	on wodgenerator.exercisetype (id);

create unique index if not exists exercisetype_name_uindex
	on wodgenerator.exercisetype (name);

