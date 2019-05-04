
-- USER DATA
insert into SYSTEM_USER (USER_ID,USERNAME,USER_PASSWORD) values (1,'salah','$2a$10$yMLDwDk2rKKksnRCb3Rclu9hEbI3ycU3Z1b5g0nk7mp8E9ZDegKVm');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(1,'ROLE_ADMIN','can edit any pages');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(2,'ROLE_EDIT_CITIZENS','can edit only citizens page');
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,1);
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,2);


-- GENDER Data
Insert into GENDER (GENDER_ID,GENDER_NAME) values (1,'ذكر');
Insert into GENDER (GENDER_ID,GENDER_NAME) values (2,'انثي');




--OCCUPATION DATA
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (56,'طبيب');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (2,'مهندس مدني');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (3,'مهندس إتصالات');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (1,'مدرس');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (4,'مهندس زراعي');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (5,'موظف');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (6,'عامل');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (61,'بالمعاش');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (62,'ميكانيكي');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (63,'حاصل على معهد');
Insert into OCCUPATION (OCCUPATION_ID,OCCUPATION_NAME) values (64,'مهندس');

-- City DATA

-- GOVERNATE DATA
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (1,'القاهره');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (2,'الاسكندريه');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (29,'الاقصر');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (19,'الاسماعيلية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (18,'البحيرة');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (21,'الجيزة');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (12,'الدقهلية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (13,'الشرقية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (16,'الغربية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (14,'القليوبية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (17,'المنوفية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (24,'المنيا');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (28,'أسوان');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (25,'أسيوط');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (22,'بني سويف');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (26,'سوهاج');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (27,'قنا');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (15,'كفر الشيخ');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (33,'مرسي مطروح');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (88,'مولود خارج الجمهورية');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (3,'بورسعيد');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (4,'السويس ');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (11,'دمياط');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (23,'الفيوم ');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (31,'البحر الأحمر');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (32,'الوادى الجديد');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (34,'شمال سيناء');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (35,'جنوب سيناء');
Insert into GOVERNATE (GOVERNATE_ID,GOVERNATE_NAME) values (37,'اخرى');



