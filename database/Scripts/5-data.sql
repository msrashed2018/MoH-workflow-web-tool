
-- REM INSERTING into MOH_WORKFLOW_SCHEMA.USER_ROLES
Insert into MOH_WORKFLOW_SCHEMA.ZONE (ZONE_ID,ZONE_NAME,ZONE_DESCRIPTION) values (1,'المجلس الطبي الرئيسي بالقاهرة','لا يوجد ملاحظات');


Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (2,'eye','$2a$10$tMPI04TYrepQCB9cmmunHOPpEboWzD6lvwJxiOsq0g.EGoCDnfbpG',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (3,'bones','$2a$10$dtmakn6rmEr7k0ctRIHCgu3j9AAcnhRcItEE88R7OQ0SjX02rYQvm',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (4,'register','$2a$10$CaLwfM/7DHErFQ354btuUuSk19NWgQHeszPpGb/1GHx0Huj3lNpv2',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (5,'medicalregister','$2a$10$e9IdbUSfoNJN9OUu7r9JKeG/9IcGpVAYkYOSdRAAsH7km2SMr7TeW',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (6,'reviewer','$2a$10$UF0W1BtHUi7xBG863OYGW.GrRMdgrmS/NdHUVr5tA5U8OA7SuOM1m',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (7,'approver','$2a$10$oFruULr1K2h8ZxQ49q3FE.xTRXSXJPdlrhB8MdWERfzIOpAzDNJy.',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (8,'citizenregister','$2a$10$twUR7P8alu1qkOVIeWd1G.R5U6bUe7rH6I1SdlzrsADDTT9YRD/W2',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (9,'cashier','$2a$10$a7fwnuxNM2yqHBkFm.kqJunvBnCpL.s/xqQbOSOr0RlKlSaxWyduG',null,1);
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_USER (USER_ID,USERNAME,PASSWORD,CREATED_DATE,ZONE_ID) values (1,'admin','$2a$10$L9MIF6lIh1bVDi7xnz4g1uZ9Ylcl73e89adMYQ6tmf/G28YnIUueq',null,1);


Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (3,'CITIZEN','اضافة و تعديل بيانات المواطنين');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (4,'CASHIER','تسجيل المدفوعات لطلبات الكشف المستعجل و الكشف المستعجل');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (5,'CONTINUE_REGISTERING','استكمال بيانات الطلب, رفع اوراق المواطن و تحديد لجان كشف العظام و الرمد');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (6,'EYE','تسجيل حضور المواطنين لكشف الرمد');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (7,'BONES','تسجيل حضور المواطنين لكشف العظام');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (8,'MEDICAL_REGISTERING','تسجيل بيانات كشف العظام و الرمد , و رفع اوارق الكشف');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (9,'REQUESTS_REVIEWER','مراجعة الطلبات و امكانية تعديل نتيجة الطلب');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (10,'REQUESTS_APPROVER','اعتماد الطلبات و امكانية تعديل النتيجة');
Insert into MOH_WORKFLOW_SCHEMA.SYSTEM_ROLE (ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION) values (1,'ADMIN','تعديل النظام بالكامل');


Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (2,2,6);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (3,3,7);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (4,4,5);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (5,5,8);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (6,6,9);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (7,7,10);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (8,8,3);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (9,9,4);
Insert into MOH_WORKFLOW_SCHEMA.USER_ROLES (ID,USER_ID,ROLE_ID) values (1,1,1);

