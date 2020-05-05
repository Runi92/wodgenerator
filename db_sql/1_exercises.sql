create table if not exists wodgenerator.exercises
(
	id serial not null
		constraint exercises_pk
			primary key,
	name varchar not null
);

comment on table wodgenerator.exercises is 'Таблица для хранения упражнений';

comment on column wodgenerator.exercises.id is 'Идентификатор';

comment on column wodgenerator.exercises.name is 'Название упражнения';

alter table wodgenerator.exercises owner to postgres;

create unique index if not exists exercises_id_uindex
	on wodgenerator.exercises (id);

create unique index if not exists exercises_name_uindex
	on wodgenerator.exercises (name);

