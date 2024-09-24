CREATE SEQUENCE seq_worker_id
    START WITH 1
    INCREMENT BY 1;
CREATE SEQUENCE seq_client_id
    START WITH 1
    INCREMENT BY 1;
CREATE SEQUENCE seq_project_id
    START WITH 1
    INCREMENT BY 1;
DO
$$
BEGIN
CREATE TYPE worker_level AS ENUM ('Trainee', 'Junior', 'Middle', 'Senior');
END
$$;

CREATE TABLE worker
(
    id       BIGINT DEFAULT nextval('seq_worker_id'),
    NAME     VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2),
    BIRTHDAY DATE CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900),
    LEVEL    worker_level  NOT NULL,
    SALARY   INT CHECK (SALARY BETWEEN 100 AND 100000),
    CONSTRAINT pk_worker_id PRIMARY KEY (id)
    );

CREATE TABLE client
(
    id   BIGINT DEFAULT nextval('seq_client_id'),
    NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2),
    CONSTRAINT pk_client_id PRIMARY KEY (id)
    );

CREATE TABLE project
(
    id          BIGINT DEFAULT nextval('seq_project_id'),
    NAME VARCHAR(255) NOT NULL,
    CLIENT_ID   BIGINT,
    START_DATE  DATE,
    FINISH_DATE DATE,
    CONSTRAINT pk_project_id PRIMARY KEY (id),
    CONSTRAINT fk_client_id FOREIGN KEY (CLIENT_ID) REFERENCES client (id)
    );

CREATE TABLE project_worker
(
    PROJECT_ID BIGINT,
    WORKER_ID  BIGINT,
    CONSTRAINT pk_project_worker_id PRIMARY KEY (PROJECT_ID, WORKER_ID),
    CONSTRAINT fk_project_id FOREIGN KEY (PROJECT_ID) REFERENCES project (id),
    CONSTRAINT fk_worker_id FOREIGN KEY (WORKER_ID) REFERENCES worker (id)
    );