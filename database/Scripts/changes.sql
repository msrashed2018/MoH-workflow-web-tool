--------------------------------------------------------
--  DDL for Table BONES_REVEAL
--------------------------------------------------------

  CREATE TABLE "MOH_WORKFLOW_SCHEMA"."BONES_REVEAL" 
   (	"BONES_REVEAL_ID" NUMBER NOT NULL ,
	"DISABILITY_ID"  NUMBER,
	"RESULT"	VARCHAR2(255 BYTE),
	"DESCRIPTION"	VARCHAR2(255 BYTE),
	"REVEAL_DONE"	NUMBER(1,0),
	"REQUEST_ID"	NUMBER NOT NULL
   );
CREATE UNIQUE INDEX "MOH_WORKFLOW_SCHEMA"."BONES_REVEAL_PK" ON "MOH_WORKFLOW_SCHEMA"."BONES_REVEAL" ("BONES_REVEAL_ID");
ALTER TABLE "MOH_WORKFLOW_SCHEMA"."BONES_REVEAL" ADD CONSTRAINT "BONES_REVEAL_PK" PRIMARY KEY ("BONES_REVEAL_ID")
 ALTER TABLE "MOH_WORKFLOW_SCHEMA"."EYE_REVEAL" MODIFY "RESULT" NULL;