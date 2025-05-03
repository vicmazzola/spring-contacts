-- create the sequence (must end with a semicolon)
CREATE SEQUENCE USERS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- create the table (NO trailing comma!)
CREATE TABLE TB_USERS (
                          USER_ID  INTEGER       DEFAULT USERS_SEQ.NEXTVAL NOT NULL,
                          NAME     VARCHAR2(100) NOT NULL,
                          EMAIL    VARCHAR2(100) UNIQUE NOT NULL,
                          PASSWORD VARCHAR2(100) NOT NULL,
                          ROLE     VARCHAR2(50)  DEFAULT 'USER'
);
