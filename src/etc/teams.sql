drop table teams
create table teams
(
teamid int primary key NOT NULL,
location varchar(80),
abbreviation varchar(10),
displayname varchar(80),
venueid int
)
