create table wodgenerator.exercise_measurementunit
(
	id serial not null
		constraint exercise_measurementunit_pk
			primary key,
	exercise_id integer not null
		constraint exercise_measurementunit_exercises_id_fk
			references wodgenerator.exercises
				on delete cascade,
	measurementunit_id integer not null
		constraint exercise_measurementunit_measurementunits_id_fk
			references wodgenerator.measurementunits
				on delete cascade
);

comment on table wodgenerator.exercise_measurementunit is 'Таблицы связи упражения и единицы измерения';

comment on column wodgenerator.exercise_measurementunit.id is 'Идентификатор';

comment on column wodgenerator.exercise_measurementunit.exercise_id is 'Идентификатор упражнения';

comment on column wodgenerator.exercise_measurementunit.measurementunit_id is 'Идентификатор единицы измерения';

alter table wodgenerator.exercise_measurementunit owner to postgres;

create unique index exercise_measurementunit_id_uindex
	on wodgenerator.exercise_measurementunit (id);

