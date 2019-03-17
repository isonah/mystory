
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (client_id VARCHAR(255) PRIMARY KEY, resource_ids VARCHAR(255), client_secret VARCHAR(255), scope VARCHAR(255), authorized_grant_types VARCHAR(255), web_server_redirect_uri VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additional_information VARCHAR(4096), autoapprove VARCHAR(255));

DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (token_id VARCHAR(255), token LONG VARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255));

DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (token_id VARCHAR(255), token LONG VARBINARY, authentication_id VARCHAR(255) PRIMARY KEY, user_name VARCHAR(255), client_id VARCHAR(255), authentication LONG VARBINARY, refresh_token VARCHAR(255));

DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (token_id VARCHAR(255), token LONG VARBINARY, authentication LONG VARBINARY);

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (code VARCHAR(255), authentication LONG VARBINARY);

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (userId VARCHAR(255), clientId VARCHAR(255), scope VARCHAR(255), status VARCHAR(10), expiresAt TIMESTAMP, lastModifiedAt TIMESTAMP);

DROP TABLE IF EXISTS ClientDetails;
CREATE TABLE ClientDetails (appId VARCHAR(255) PRIMARY KEY, resourceIds VARCHAR(255), appSecret VARCHAR(255), scope VARCHAR(255), grantTypes VARCHAR(255), redirectUrl VARCHAR(255), authorities VARCHAR(255), access_token_validity INTEGER, refresh_token_validity INTEGER, additionalInformation VARCHAR(4096), autoApproveScopes VARCHAR(255));

DROP TABLE IF EXISTS SPRING_SESSION_ATTRIBUTES;
DROP TABLE IF EXISTS SPRING_SESSION;
CREATE TABLE SPRING_SESSION (SESSION_ID CHAR(36) NOT NULL, CREATION_TIME BIGINT NOT NULL, LAST_ACCESS_TIME BIGINT NOT NULL, MAX_INACTIVE_INTERVAL INT NOT NULL, PRINCIPAL_NAME VARCHAR(100), CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (SESSION_ID)) ENGINE = InnoDB;

CREATE INDEX SPRING_SESSION_IX1
  ON SPRING_SESSION (LAST_ACCESS_TIME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (SESSION_ID CHAR(36) NOT NULL, ATTRIBUTE_NAME VARCHAR(200) NOT NULL, ATTRIBUTE_BYTES BLOB NOT NULL, CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_ID, ATTRIBUTE_NAME), CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_ID) REFERENCES SPRING_SESSION (SESSION_ID)
  ON DELETE CASCADE) ENGINE = InnoDB;

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1
  ON SPRING_SESSION_ATTRIBUTES (SESSION_ID);

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS USERS;


CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  email VARCHAR(254) NOT NULL,
  PRIMARY KEY (username));




create table authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  foreign key (username) references users (username),
  unique index authorities_idx_1 (username, authority)
) engine = InnoDb;

INSERT INTO users(username,password,enabled, email)
VALUES ('priya','priya', true, 'priya@live.fr');
INSERT INTO users(username,password,enabled, email)
VALUES ('naveen','naveen', true, 'priya@live.fr');