
-- USER DATA
insert into SYSTEM_USER (USER_ID,USERNAME,PASSWORD) values (1,'admin','$2a$10$L9MIF6lIh1bVDi7xnz4g1uZ9Ylcl73e89adMYQ6tmf/G28YnIUueq');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(1,'ROLE_ADMIN','can edit any pages');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(2,'ROLE_CustomLift','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(3,'ROLE_Eye','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(4,'ROLE_EditCitizen','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(5,'ROLE_LetterReview','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(6,'ROLE_EditCitizenAlex','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(7,'ROLE_Register','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(8,'ROLE_Tawsya','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(9,'ROLE_Review','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(10,'ROLE_TawsyaReview','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(11,'ROLE_Confirm','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(12,'ROLE_Inquery','');
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,1);
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,2);


-- EQUIPMENT Data
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل تدار باليد وفاصل حركة اوتوماتيك','دواسة البنزين والفرامل تدار باليد وفاصل حركة اوتوماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل يدار باليد','دواسة البنزين والفرامل يدار باليد');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة يدار باليد','دواسة فاصل الحركة يدار باليد');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة هيدروماتيك','دواسة فاصل الحركة هيدروماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليسري بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك','مفاتيح التشغيل والاناره بالجهة اليسري بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك','مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية مزودة ببكره ومنيم بالكف الصناعي + ناقل سرعة اوتوماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليسري بالإضافة إلي عجله قيادة مائية مزوده ببكره ومنيم باليد اليمني+ ناقل سرعة هيدروماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية مزوده ببكره ومنيم باليد اليسري + ناقل سرعة هيدروماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليمني بالإضافة إلي عجلة قياده مائيه والبنزين والفرامل يدار باليد وفاصل حركه اوتوماتيك ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('قيادة يدوية كاملة  دواسة البنزين والفرامل وفاصل الحركة يدار باليد ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليسري بالإضافة إلي عجلة قياده مائيه والبنزين والفرامل يدار باليد وفاصل حركه هيدروماتيك ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليسري بالإضافة إلي عجله قيادة مائية + ناقل سرعة اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية + ناقل سرعة اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('البنزين والفرامل يدار باليد وعجلة قياده مائيه وفاصل حركه هيدروماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('البنزين والفرامل يدار باليد وعجلة قياده مائيه وفاصل حركه هيدروماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل تدار باليد وفاصل الحركة يدار باليد أو اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة تدار باليد أو اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة تدار باليد أو اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليسري بالإضافة إلي عجلة قياده مائيه مزوده ببكره ومنيم بالكف الصناعي+دواسة البنزين والفرامل تدار باليد وفاصل حركه هيدروماتيك	','');

--
---- GENDER Data
Insert into GENDER (GENDER_NAME) values ('ذكر');
Insert into GENDER (GENDER_NAME) values ('انثي');

---- ZONE Data
Insert into ZONE (ZONE_NAME) values('المجلس الطبي الرئيسي بالقاهرة');
Insert into ZONE (ZONE_NAME) values('المجلس الطبي العام بسوهاج');
Insert into ZONE (ZONE_NAME) values('مجلس طبي قنا');
Insert into ZONE (ZONE_NAME) values('مجلس طبي بورسعيد');
Insert into ZONE (ZONE_NAME) values('مجلس طبي السويس');
Insert into ZONE (ZONE_NAME) values('مجلس طبي دمياط');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الدقهلية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الشرقية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي القليوبية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي كفر الشيخ');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الغربية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي البحيرة');
Insert into ZONE (ZONE_NAME) values('مجلس طبي المنوفية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الاسماعيلية');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الجيزة');
Insert into ZONE (ZONE_NAME) values('مجلس طبي بني سويف');
Insert into ZONE (ZONE_NAME) values('مجلس طبي المنيا');
Insert into ZONE (ZONE_NAME) values('مجلس طبي أسيوط');
Insert into ZONE (ZONE_NAME) values('مجلس طبي البحر الأحمر');
Insert into ZONE (ZONE_NAME) values('مجلس طبي أسوان');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الأقصر');
Insert into ZONE (ZONE_NAME) values('مجلس طبي الوادي الجديد');
Insert into ZONE (ZONE_NAME) values('مجلس طبي شمال سيناء');
Insert into ZONE (ZONE_NAME) values('مجلس طبي جنوب سيناء');
--
--
----OCCUPATION DATA
Insert into OCCUPATION (OCCUPATION_NAME) values ('طبيب');
Insert into OCCUPATION (OCCUPATION_NAME) values ('مهندس مدني');
Insert into OCCUPATION (OCCUPATION_NAME) values ('مهندس إتصالات');
Insert into OCCUPATION (OCCUPATION_NAME) values ('مدرس');
Insert into OCCUPATION (OCCUPATION_NAME) values ('مهندس زراعي');
Insert into OCCUPATION (OCCUPATION_NAME) values ('موظف');
Insert into OCCUPATION (OCCUPATION_NAME) values ('عامل');
Insert into OCCUPATION (OCCUPATION_NAME) values ('بالمعاش');
Insert into OCCUPATION (OCCUPATION_NAME) values ('ميكانيكي');
Insert into OCCUPATION (OCCUPATION_NAME) values ('حاصل على معهد');
Insert into OCCUPATION (OCCUPATION_NAME) values ('مهندس');



---- GOVERNATE DATA
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 1,'القاهره');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (2,'الاسكندريه');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (29,'الاقصر');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (19,'الاسماعيلية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (18,'البحيرة');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (21,'الجيزة');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (12,'الدقهلية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (13,'الشرقية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values (16,'الغربية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 14,'القليوبية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 17,'المنوفية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 24,'المنيا');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 28,'أسوان');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 25,'أسيوط');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 22,'بني سويف');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 26,'سوهاج');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 27,'قنا');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 15,'كفر الشيخ');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 33,'مرسي مطروح');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 88,'مولود خارج الجمهورية');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 3,'بورسعيد');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 4,'السويس ');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 11,'دمياط');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 23,'الفيوم ');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 31,'البحر الأحمر');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 32,'الوادى الجديد');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 34,'شمال سيناء');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 35,'جنوب سيناء');
Insert into GOVERNATE (GOVERNATE_CODE, GOVERNATE_NAME) values ( 37,'اخرى');


---- City DATA
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(6,'الشيخ زايد');


---- EYE MEASURE DATE
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/6','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/12','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/18','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/24','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/36','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('6/60','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE, MEASURE_DESCRIPTION) values ('5/60','لاتوجد ملاحظات');
insert into EYE_MEASURE (MEASURE_TITLE) values ('أقل من 6/60');
insert into EYE_MEASURE (MEASURE_TITLE) values ('لاتبصر الضوء');
insert into EYE_MEASURE (MEASURE_TITLE) values ('تبصر الضوء');
insert into EYE_MEASURE (MEASURE_TITLE) values ('تبصر حركة يد');
insert into EYE_MEASURE (MEASURE_TITLE) values ('عد أصابع');
insert into EYE_MEASURE (MEASURE_TITLE) values ('خيال');
insert into EYE_MEASURE (MEASURE_TITLE) values ('عين زجاجيه');
insert into EYE_MEASURE (MEASURE_TITLE) values ('عدسات لاصقه');


