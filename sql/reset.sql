delete from coalition where coalition_status !=3;
update task set task_status=0 where task_status !=3;
delete from coalition_company where coalition_id not in (select id from coalition);
update company set status=0 where status !=3;