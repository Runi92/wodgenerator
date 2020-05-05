create table wodgenerator.exercise_trainingpart
(
	id serial not null
		constraint exercise_trainingpart_pk
			primary key,
	exercise_id integer not null
		constraint exercise_trainingpart_exercises_id_fk
			references wodgenerator.exercises
				on delete cascade,
	trainingpart_id integer not null
		constraint exercise_trainingpart_trainingpart_id_fk
			references wodgenerator.trainingpart
				on delete cascade
);

comment on table wodgenerator.exercise_trainingpart is 'Таблица для связи упражнений и частей тренировки';

comment on column wodgenerator.exercise_trainingpart.id is 'Идентификатор';

comment on column wodgenerator.exercise_trainingpart.exercise_id is 'Идентификатор упражнения';

comment on column wodgenerator.exercise_trainingpart.trainingpart_id is 'Идентификатор части тренировки';

alter table wodgenerator.exercise_trainingpart owner to postgres;

create unique index exercise_trainingpart_id_uindex
	on wodgenerator.exercise_trainingpart (id);

