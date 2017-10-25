insert into eg_action(id, name,url,servicecode,queryparams,parentmodule,ordernumber,displayname,enabled,createdby,createddate,lastmodifiedby,lastmodifieddate)values(nextval('SEQ_EG_ACTION'),'CalendarHolidayTypesSearch','/egov-common-masters/holidaytypes/_search','CalendarHolidays',null,(select id from service where name='EGOV Common Masters' and tenantid='default'),1,'CalendarHolidayTypesSearch',false,1,now(),1,now());

insert into eg_roleaction(roleCode,actionid,tenantid)values('EMPLOYEE ADMIN', (select id from eg_action where name = 'CalendarHolidayTypesSearch'),'default');
insert into eg_roleaction(roleCode,actionid,tenantid)values('SUPERUSER', (select id from eg_action where name = 'CalendarHolidayTypesSearch'),'default');
insert into eg_roleaction(roleCode,actionid,tenantid)values('EMPLOYEE', (select id from eg_action where name = 'CalendarHolidayTypesSearch'),'default');