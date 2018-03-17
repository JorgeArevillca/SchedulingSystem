# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table course (
  code                          bigint auto_increment not null,
  title                         varchar(255),
  description                   varchar(255),
  constraint pk_course primary key (code)
);

create table course_student (
  course_code                   bigint not null,
  student_student_id            bigint not null,
  constraint pk_course_student primary key (course_code,student_student_id)
);

create table student (
  student_id                    bigint auto_increment not null,
  last_name                     varchar(255),
  first_name                    varchar(255),
  constraint pk_student primary key (student_id)
);

create table student_course (
  student_student_id            bigint not null,
  course_code                   bigint not null,
  constraint pk_student_course primary key (student_student_id,course_code)
);

alter table course_student add constraint fk_course_student_course foreign key (course_code) references course (code) on delete restrict on update restrict;
create index ix_course_student_course on course_student (course_code);

alter table course_student add constraint fk_course_student_student foreign key (student_student_id) references student (student_id) on delete restrict on update restrict;
create index ix_course_student_student on course_student (student_student_id);

alter table student_course add constraint fk_student_course_student foreign key (student_student_id) references student (student_id) on delete restrict on update restrict;
create index ix_student_course_student on student_course (student_student_id);

alter table student_course add constraint fk_student_course_course foreign key (course_code) references course (code) on delete restrict on update restrict;
create index ix_student_course_course on student_course (course_code);


# --- !Downs

alter table course_student drop foreign key fk_course_student_course;
drop index ix_course_student_course on course_student;

alter table course_student drop foreign key fk_course_student_student;
drop index ix_course_student_student on course_student;

alter table student_course drop foreign key fk_student_course_student;
drop index ix_student_course_student on student_course;

alter table student_course drop foreign key fk_student_course_course;
drop index ix_student_course_course on student_course;

drop table if exists course;

drop table if exists course_student;

drop table if exists student;

drop table if exists student_course;

