create table to_do_list(
task_id integer(5) AUTO_INCREMENT PRIMARY KEY,
task_title varchar(20),
task_description varchar(150),
task_priority varchar(6),
task_due_date Date,
task_creation_date Date,
task_isCompleted boolean
);



insert into TABLE to_do_list VALUES('','sdsdvsdvdv','sd rgfvsdfdvc','high',TO_DATE('2019,07,02','YYYY,MM,DD'),TO_DATE('2019,07,03','YYYY,MM,DD'),'0');

select * from to_do_list;


drop table to_do_list