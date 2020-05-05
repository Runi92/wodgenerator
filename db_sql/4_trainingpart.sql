create table wodgenerator.trainingpart
(
	id serial not null
		constraint trainingpart_pk
			primary key,
	name varchar not null
);

comment on table wodgenerator.trainingpart is 'Таблица для хранения части тренировки';

comment on column wodgenerator.trainingpart.id is 'Идентификатор';

comment on column wodgenerator.trainingpart.name is 'Название части тренировки';

alter table wodgenerator.trainingpart owner to postgres;

create unique index trainingpart_id_uindex
	on wodgenerator.trainingpart (id);

create unique index trainingpart_name_uindex
	on wodgenerator.trainingpart (name);

