
drop table venues

create table venues
(
  venueid int primary key NOT NULL,
  name varchar(80),
  city varchar(80)
)