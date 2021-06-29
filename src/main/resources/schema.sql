drop table if exists note;

create table note(
id bigint auto_increment primary key,
text varchar(1000),
creationDate date,
hashtags varchar(1000),
header varchar(100)
)