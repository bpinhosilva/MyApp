# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table task (
  id                            bigint auto_increment not null,
  name                          varchar(255) not null,
  done                          tinyint(1) default 0,
  due_date                      datetime(6),
  constraint pk_task primary key (id)
);


# --- !Downs

drop table if exists task;

