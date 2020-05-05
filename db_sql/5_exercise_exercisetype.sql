create table wodgenerator.exercise_exercisetype
(
	id serial not null
		constraint exercise_exercisetype_pk
			primary key,
	exercise_id integer not null
		constraint exercise_exercisetype_exercises_id_fk
			references wodgenerator.exercises
				on delete cascade,
	exercisetype_id integer not null
		constraint exercise_exercisetype_exercisetype_id_fk
			references wodgenerator.exercisetype
				on delete cascade
);

comment on table wodgenerator.exercise_exercisetype is 'Таблица для связи упражнения и типа упражнения';

comment on column wodgenerator.exercise_exercisetype.id is 'Идентификатор';

comment on column wodgenerator.exercise_exercisetype.exercise_id is 'Идентификатор упражнения';

comment on column wodgenerator.exercise_exercisetype.exercisetype_id is 'Идентификатор типа упражнения';

alter table wodgenerator.exercise_exercisetype owner to postgres;

create unique index exercise_exercisetype_id_uindex
	on wodgenerator.exercise_exercisetype (id);

