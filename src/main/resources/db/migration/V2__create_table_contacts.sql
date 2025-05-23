CREATE SEQUENCE CONTACTS_SEQ
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE TB_CONTACTS
(
    ID         INTEGER DEFAULT CONTACTS_SEQ.NEXTVAL NOT NULL,
    NAME       VARCHAR2(100) NOT NULL,
    EMAIL      VARCHAR2(100) NOT NULL,
    BIRTH_DATE DATE                                 NOT NULL
);

