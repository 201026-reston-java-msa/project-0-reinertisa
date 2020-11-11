DROP TABLE IF EXISTS ROLE cascade;
DROP TABLE IF EXISTS account cascade;
DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS accountstatus;
DROP TABLE IF EXISTS accounttype;

CREATE TABLE users(
	
		userid SERIAL PRIMARY KEY,
		username VARCHAR(50) NOT NULL UNIQUE,
		password VARCHAR(60) NOT NULL,
		firstname VARCHAR(50) NOT NULL,
		lastname VARCHAR(50) NOT NULL,
		email VARCHAR(50) NOT NULL,
		
		roleid INTEGER NOT NULL,
		CONSTRAINT fk_roleid
		FOREIGN KEY (roleid) REFERENCES role(roleid),
		
		logintime timestamp
		
);

INSERT INTO users (username, "password" , firstname , lastname , email, roleid, logintime)
values('admin', '123', 'admin', 'admin', 'admin@bankapplication.com',1, current_timestamp);
INSERT INTO users (username, "password" , firstname , lastname , email, roleid, logintime)
values('employee', '123', 'emloyee', 'employee', 'employee@bankapplication.com',2, current_timestamp);

UPDATE users SET roleid=1 WHERE userid=1;
UPDATE users SET roleid=2 WHERE userid=2;

SELECT * FROM users;


CREATE TABLE account(
	accountid SERIAL PRIMARY KEY,
	--balance DOUBLE PRECISION NOT NULL,
	balance NUMERIC(10,2),
	
	userid INTEGER NOT NULL,
	CONSTRAINT fk_users
	FOREIGN KEY (userid) REFERENCES users(userid),
	
	statusid INTEGER NOT NULL,
	CONSTRAINT fk_accountstatus
	FOREIGN KEY (statusid) REFERENCES accountstatus(statusid),

	typeid INTEGER NOT NULL,
	CONSTRAINT fk_accounttype
	FOREIGN KEY (typeid) REFERENCES accounttype(typeid),
	
	owner VARCHAR(50) NOT NULL,
	createDate DATE
);



SELECT * FROM account;

SELECT * FROM accountstatus;


CREATE TABLE role(
		roleid INTEGER NOT NULL PRIMARY KEY,
		role VARCHAR(50) NOT NULL
);

INSERT INTO ROLE (roleid, role) VALUES (1, 'Admin');
INSERT INTO ROLE (roleid, role) VALUES (2, 'Employee');
INSERT INTO ROLE (roleid, role) VALUES (3, 'Standard');
INSERT INTO ROLE (roleid, role) VALUES (4, 'Premium');

SELECT * FROM role;

CREATE TABLE accounttype(
	typeid SERIAL PRIMARY KEY,
	type VARCHAR(50) NOT NULL UNIQUE  
);

INSERT INTO accounttype (type) VALUES ('Checking');
INSERT INTO accounttype (type) VALUES ('Savings');

SELECT * FROM accounttype;

CREATE TABLE accountstatus(
	statusid SERIAL PRIMARY KEY,
	status VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO accountstatus (status) VALUES('Pending');
INSERT INTO accountstatus (status) VALUES('Open');
INSERT INTO accountstatus (status) VALUES('Closed');
INSERT INTO accountstatus (status) VALUES('Denied');

SELECT * FROM accountstatus;


CREATE TABLE localweather(
	
		wid SERIAL PRIMARY KEY,
		cityname VARCHAR(50) NOT NULL UNIQUE,
		countryname VARCHAR(50) NOT NULL,
		temp NUMERIC(10,2),
		feels_like NUMERIC(10,2),
		weather VARCHAR(50) NOT NULL
		
);

SELECT * FROM localweather;
TRUNCATE localweather;

CREATE TABLE stockmarket(
	
		sid SERIAL PRIMARY KEY,
		symbol VARCHAR(50) NOT NULL UNIQUE,
		datetime VARCHAR(50),
		open NUMERIC(10,2),
		high NUMERIC(10,2),
		low NUMERIC(10,2),
		close NUMERIC(10,2),
		volume INTEGER
		
);

SELECT * FROM stockmarket;

SELECT * FROM stockmarket;
DROP TABLE stockmarket;

SELECT * FROM users;
SELECT * FROM account;


SELECT a.accountid, round(a.balance :: NUMERIC, 2) AS balance, 
b.status , b.statusid ,c.TYPE, c.typeid, a.OWNER, a.createdate 
FROM account a 
INNER JOIN accountstatus b ON a.statusid = b.statusid 
INNER JOIN accounttype c ON a.typeid = c.typeid ORDER BY a.owner;


SELECT a.accountid, trunc(balance :: NUMERIC, 2) AS balance, 
a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate FROM account a 
INNER JOIN accountstatus b ON a.statusid  = b.statusid 
INNER JOIN accounttype c ON a.typeid = c.typeid 
WHERE a.statusid = 1 ORDER BY a.owner;

SELECT a.accountid, trunc(balance :: NUMERIC, 2) AS balance, 
a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate  
FROM account a 
INNER JOIN accountstatus b ON a.statusid = b.statusid 
INNER JOIN accounttype c ON a.typeid = c.typeid 
WHERE c.typeid = 1 ORDER BY a.owner;

SELECT a.accountid, trunc(balance :: NUMERIC, 2) AS balance, 
a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate  
FROM account a 
INNER JOIN accountstatus b ON a.statusid = b.statusid 
INNER JOIN accounttype c ON a.typeid = c.typeid 
WHERE userid=3 ORDER BY a.accountid DESC;

SELECT a.accountid, trunc(balance :: NUMERIC, 2) AS balance, 
a.statusid, a.typeid ,b.status ,c.TYPE, a.OWNER, a.createdate 
FROM account a 
INNER JOIN accountstatus b ON a.statusid = b.statusid 
INNER JOIN accounttype c ON a.typeid = c.typeid 
WHERE a.accountid = 1;


UPDATE users SET logintime=current_timestamp WHERE userid=2;
SELECT * FROM users;

SELECT logintime FROM users WHERE userid=1;

SELECT * FROM account;

SELECT current_timestamp;



SELECT * FROM account;



CREATE OR REPLACE FUNCTION hellow2() RETURNS SETOF "account"
AS
$$
SELECT * FROM "account";
$$ LANGUAGE SQL;

SELECT hellow2();



CREATE OR REPLACE FUNCTION getdata(x int) RETURNS Integer
AS 
$$

SELECT accountid FROM account WHERE accountid = x;

$$ LANGUAGE SQL;

SELECT getdata(1); 



CREATE OR REPLACE FUNCTION sophia(val NUMERIC) RETURNS NUMERIC 
AS 

$$
	DECLARE 
	new_id NUMERIC;

BEGIN 

SELECT accountid INTO new_id FROM account WHERE accountid = val;
RETURN new_id;

END


$$ LANGUAGE PLPGSQL;

SELECT sophia(9);

DROP FUNCTION getallaccounts()


SELECT * FROM account;
SELECT * FROM users;





SELECT * FROM users ;

SELECT * FROM account ORDER BY "owner" 












