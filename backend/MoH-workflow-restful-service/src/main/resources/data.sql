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

-- USER DATA
insert into SYSTEM_USER (USER_ID,USERNAME,PASSWORD,ZONE_ID) values (1,'admin','$2a$10$L9MIF6lIh1bVDi7xnz4g1uZ9Ylcl73e89adMYQ6tmf/G28YnIUueq',1);
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(1,'ADMIN','can edit any pages');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(2,'CustomLift','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(3,'Eye','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(4,'EditCitizen','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(5,'LetterReview','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(6,'EditCitizenAlex','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(7,'Register','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(8,'Tawsya','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(9,'Review','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(10,'TawsyaReview','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(11,'Confirm','');
insert into SYSTEM_ROLE (ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION) values(12,'Inquery','');
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,1);
insert into SYSTEM_USER_ROLES (USERS_USER_ID, ROLES_ROLE_ID) VALUES(1,2);


-- EQUIPMENT Data
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل تدار باليد وفاصل حركة اوتوماتيك','دواسة البنزين والفركة اوتوماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل يدار باليد','دواسة البنزين والفرامل يدار باليد');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة يدار باليد','دواسة فاصل الحركة يدار باليد');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة هيدروماتيك','دواسة فاصل الحركة هيدروماتيك');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('م ناقل سرعة اوتوماتيك','مفاتيح التشغيل والاناره بالجهة اليم');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليسري بالإضافة إلي عجله ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية مزوده ببكره ك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليمني بالإضافة إلي عجلة قياده مائيه والبناتيك ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('قيادة يدوية كاملة  دواسة البنزين والفرامل وفاصل الحركة يدار باليد ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليسري بالإضافة إلي عجلة قياده صل حركه هيدروماتيك ','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهة اليمني بالإضافة إلي عجله قيادة مائية + ناقل سرعة اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('البنزين والفرامل يدار باليد وعجلة قياده مائيه وفاصل حركه هيدروماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة البنزين والفرامل تدار باليد وفاصل الحركة يدار باليد أو اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('دواسة فاصل الحركة تدار باليد أو اوتوماتيك','');
Insert into EQUIPMENT (EQUIPMENT_NAME,EQUIPMENT_DESCRIPTION) values ('مفاتيح التشغيل والاناره بالجهه اليسري بالإضافة إلدروماتيك, ','');

--
---- GENDER Data
--Insert into GENDER (GENDER_NAME) values ('ذكر');
--Insert into GENDER (GENDER_NAME) values ('انثي');


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
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 1,'القاهره');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1,2,'الاسكندريه');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1,29,'الاقصر');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 19,'الاسماعيلية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 18,'البحيرة');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 21,'الجيزة');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 12,'الدقهلية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 13,'الشرقية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values (1, 16,'الغربية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 14,'القليوبية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 17,'المنوفية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 24,'المنيا');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 28,'أسوان');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 25,'أسيوط');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 22,'بني سويف');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 26,'سوهاج');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 27,'قنا');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 15,'كفر الشيخ');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 33,'مرسي مطروح');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 88,'مولود خارج الجمهورية');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 3,'بورسعيد');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 4,'السويس ');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 11,'دمياط');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 23,'الفيوم ');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 31,'البحر الأحمر');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 32,'الوادى الجديد');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 34,'شمال سيناء');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 35,'جنوب سيناء');
Insert into GOVERNATE (ZONE_ID, GOVERNATE_CODE, GOVERNATE_NAME) values ( 1, 37,'اخرى');


---- City DATA
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(6,'الشيخ زايد');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(1,'المعادي');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(2,'مدينة س');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(3,'مدينة 3');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(4,'مدينة 4');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(5,'مدينة 5');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(7,'مدينة 6');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(8,'مدينة 7');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(9,'مدينة 8');

Insert into CITY (GOVERNATE_ID,CITY_NAME) values(10,'مدينة 9');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(11,'مدينة 10');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(12,'مدينة 11');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(13,'مدينة 12');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(14,'مدينة 13');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(15,'مدينة 14');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(16,'مدينة 15');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(17,'مدينة 16');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(18,'مدينة 17');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(19,'مدينة 18');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(20,'مدينة 19');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(21,'مدينة 20');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(22,'مدينة 21');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(23,'مدينة 22');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(24,'مدينة 23');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(25,'مدينة 24');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(26,'مدينة 25');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(27,'مدينة 26');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(28,'مدينة 27');
Insert into CITY (GOVERNATE_ID,CITY_NAME) values(29,'مدينة 28');



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


-- REQUEST_TYPE DATA
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تظلم عيون');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تظلم عظام وعيون');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'مد صلاحيه');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(200, 'كشف عادي');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'إعادة مناظرة تظلم عظام');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(400, 'كشف مستعجل');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'إعادة مناظره عظام');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تجديد نتيجة كشف طبي');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تصويب تجهيزه');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'صحة تشخيص');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'صحة تشخيص وتجهيزه');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'إعادة مناظرة عيون');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'إعادة مناظرة  تظلم عيون');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تظلم استثنائي عظام');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'تظلم استثنائي عيون');
insert into REQUEST_TYPE (REQUEST_TYPE_PRICE, REQUEST_TYPE_NAME) values(0, 'إعادة مناظرة تجديد');


-- REQUEST_STATUS DATA

insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قرار ملغي');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قرار ملغي للتعديل');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('خطاب اداري-مراجعه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('يعاد مناظرته-مراجعه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('تم التسجيل');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('توصيه نهائيه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قرار نهائي');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('خطاب اداري');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('يعاد مناظرته');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('رفض');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('مد صلاحيه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('خطاب اداري- تعديل');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('يعاد مناظرته-تعديل');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('توصيه مبدئيه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('تعديل توصيه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قرار ملغي لانتهاء المده');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('لم يحضر');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('حفظ القرار لانتهاء المده');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('إعادة مناظرة تجديد - مراجعه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('إعادة مناظرة تجديد');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قبول التجديد - مراجعه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('قبول التجديد');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('رفض التجديد - مراجعه');
insert into REQUEST_STATUS (REQUEST_STATUS_NAME) values('رفض التجديد');


-- DISABILITY DATA
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 1,'بتر بالطرفين السفليين');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 2,'تيبس كامل بمفصل الركبة اليمني يبعد الطرفين السفليين عن الدوسات');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 3,'تيبس كامل بمفصل الركبة اليسري يبعد الطرفين السفليين عن الدوسات');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 4,'شلل بالطرف السفلي الأيمن مؤثر علي القوة العضلية وحركة المفاصل ');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 5,'شلل بالطرف السفلي الأيسر مؤثر علي القوة العضلية وحركة المفاصل');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 6,'بتر بالطرف السفلي الأيمن مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 7,'بتر بالطرف السفلي الأيسر مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('1', 8,'ضعف بعضلات الطرف السفلي الأيمن مؤثر علي القوة العضلية وحركة المفاصل');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 9,'ضعف بعضلات الطرف السفلي الأيسر مؤثر علي القوة العضلية وحركة المفاصل');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 10,'تيبس بالكاحل الأيمن مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 11,'تيبس بالكاحل الأيسر مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 12,'سقوط بالقدم اليمني مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 13,'سقوط بالقدم اليسري مؤثر');
insert into DISABILITY(ACCEPTED, EQUIPMENT_ID, DISABILITY_NAME) values('0', 14,'بتر بالطرف العلوي الأيمن ويرتدي طرف صناعي وظيفي يعمل');






insert into CITIZEN ( ADDRESS, BIRTH_DATE, CREATED_BY, CREATED_DATE, MOBILE_NO, MODIFIED_BY, MODIFIED_DATE, CITIZEN_NAME, NATIONAL_ID, CITY_ID, GENDER, GOVERNATE_ID, OCCUPATION_ID)
values('october', '1989-06-20 03:00:00', 'admin', '2019-06-20', '01015090111', null, null,  'محمد صلاح راشد', '29106202101140', 2, 'ذكر', 1, 3 );

insert into CITIZEN ( ADDRESS, BIRTH_DATE, CREATED_BY, CREATED_DATE, MOBILE_NO, MODIFIED_BY, MODIFIED_DATE, CITIZEN_NAME, NATIONAL_ID, CITY_ID, GENDER, GOVERNATE_ID, OCCUPATION_ID)
values('october', '1989-06-20 03:00:00', 'admin', '2019-06-22', '01015090111', null, null,  'احمد صلاح راشد', '29106202101141', 2, 'ذكر', 1, 3 );

insert into CITIZEN ( ADDRESS, BIRTH_DATE, CREATED_BY, CREATED_DATE, MOBILE_NO, MODIFIED_BY, MODIFIED_DATE, CITIZEN_NAME, NATIONAL_ID, CITY_ID, GENDER, GOVERNATE_ID, OCCUPATION_ID)
values('october', '1989-06-20 03:00:00', 'admin', '2019-06-24', '01015090111', null, null,  'حسن صلاح راشد', '29106202101142', 2, 'ذكر', 1, 3 );

insert into CITIZEN ( ADDRESS, BIRTH_DATE, CREATED_BY, CREATED_DATE, MOBILE_NO, MODIFIED_BY, MODIFIED_DATE, CITIZEN_NAME, NATIONAL_ID, CITY_ID, GENDER, GOVERNATE_ID, OCCUPATION_ID)
values('october', '1989-06-20 03:00:00', 'admin', '2019-06-26', '01015090111', null, null,  'سعيد صلاح راشد', '29106202101143', 2, 'ذكر', 1, 3 );


--insert into request ( CREATED_BY  , DESCRIPTION  , MODIFIED_BY  , MODIFIED_DATE  , REQUEST_DATE  , STATE  , CITIZEN_ID  , CUSTOM_ID  , REQUEST_STATUS_ID  , REQUEST_TYPE_ID  , TRAFFIC_MANAGEMENT_ID )
--values ('admin', null, null, null, now(), 'NEW', 1, null, null, 4, null);



-- COMMITTEE_MEMBER DATA

insert into COMMITTEE_MEMBER (ZONE_ID, MEMBER_MOBILE_NO, MEMBER_NAME, MEMBER_TITLE)  values (1, '010150905555', 'محمد صلاح','طبيب رمد');
insert into COMMITTEE_MEMBER (ZONE_ID, MEMBER_MOBILE_NO, MEMBER_NAME, MEMBER_TITLE)  values (1, '010150905555', 'احمد صلاح','طبيب عظام');
insert into COMMITTEE_MEMBER (ZONE_ID, MEMBER_MOBILE_NO, MEMBER_NAME, MEMBER_TITLE)  values (1, '010150905555', 'سيد صلاح','مقرر');
insert into COMMITTEE_MEMBER (ZONE_ID, MEMBER_MOBILE_NO, MEMBER_NAME, MEMBER_TITLE)  values (1, '010150905555', 'مي صلاح','مهندس');
insert into COMMITTEE_MEMBER (ZONE_ID, MEMBER_MOBILE_NO, MEMBER_NAME, MEMBER_TITLE)  values (1, '010150905555', 'ابراهيم صلاح','عضو');
 


-- COMMITTEE DATA
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-20 02:00:00',1,2,3,4,5, null,1,'عظام','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-21 02:00:00',1,2,3,4,5, null,1,'عظام','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-08-11 02:00:00',1,2,3,4,5, null,1,'عظام','تظلمات' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-23 02:00:00',1,2,3,4,5, null,1,'عظام','تظلمات' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-09-30 02:00:00',1,2,3,4,5, null,1,'عظام','اخري' );

insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-20 02:00:00',1,5,null,null,null, null,1,'رمد','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-24 02:00:00',1,5,null,null,null, null,1,'رمد','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-08-20 02:00:00',1,5,null,null,null, null,1,'رمد','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-08-10 02:00:00',1,5,null,null,null, null,1,'رمد','اخري' );
insert into COMMITTEE (COMMITTEE_DATE, MEMBER_ONE_ID, MEMBER_TWO_ID, MEMBER_THREE_ID, MEMBER_FOUR_ID, MEMBER_FIVE_ID, MEMBER_SIX_ID, ZONE_ID,  COMMITTEE_TYPE, COMMITTEE_FUNCTION) values ('2019-06-30 02:00:00',1,5,null,null,null, null,1,'رمد','اخري' );




-- CUSTOM DATA
insert into CUSTOM (CUSTOM_NAME) values('بورسعيد');
insert into CUSTOM (CUSTOM_NAME) values('الاسكندرية');
insert into CUSTOM (CUSTOM_NAME) values('دمياط');
insert into CUSTOM (CUSTOM_NAME) values('شبين الكوم');
insert into CUSTOM (CUSTOM_NAME) values('السويس');
insert into CUSTOM (CUSTOM_NAME) values('مدينة نصر');


-- TRAFFIC_MANAGEMENT DATA
insert into TRAFFIC_MANAGEMENT (TRAFFIC_NAME) values('إدارة مرور الجيزة');
insert into TRAFFIC_MANAGEMENT (TRAFFIC_NAME) values('إدارة مرور القاهرة');
insert into TRAFFIC_MANAGEMENT (TRAFFIC_NAME) values('إدارة مرور الاسكندرية');











