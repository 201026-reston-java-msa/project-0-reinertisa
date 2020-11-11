/*GET ALL ACCOUNTS STORED PROCEDURE */

CREATE OR REPLACE FUNCTION get_all_accounts() 
RETURNS TABLE(accountid INTEGER, balance NUMERIC, status VARCHAR, statusid INTEGER, type VARCHAR, typeid INTEGER, owner VARCHAR, createdate DATE)
AS $$

	SELECT a.accountid, a.balance, b.status, b.statusid, c.type, c.typeid, a.owner, a.createdate 
	FROM account a 
	INNER JOIN accountstatus b ON a.statusid = b.statusid 
	INNER JOIN accounttype c ON a.typeid = c.typeid ORDER BY a.owner;

$$ LANGUAGE SQL;

SELECT * FROM get_all_accounts();


/*GET ALL ACCOUNTS BY STATUS ID STORED PROCEDURE */

CREATE OR REPLACE FUNCTION get_all_accounts_by_statusid(id INTEGER) 
RETURNS TABLE(accountid INTEGER, balance NUMERIC, status VARCHAR, statusid INTEGER, type VARCHAR, typeid INTEGER, owner VARCHAR, createdate DATE)
AS $$

	SELECT a.accountid, a.balance, b.status, b.statusid, c.type, c.typeid, a.owner, a.createdate 
	FROM account a 
	INNER JOIN accountstatus b ON a.statusid  = b.statusid 
	INNER JOIN accounttype c ON a.typeid = c.typeid 
	WHERE a.statusid = id ORDER BY a.owner;

$$ LANGUAGE SQL;

SELECT * FROM get_all_accounts_by_statusid(1);


/*GET ALL ACCOUNTS BY USER ID STORED PROCEDURE */

CREATE OR REPLACE FUNCTION get_all_accounts_by_userid(id INTEGER) 
RETURNS TABLE(accountid INTEGER, balance NUMERIC, status VARCHAR, statusid INTEGER, type VARCHAR, typeid INTEGER, owner VARCHAR, createdate DATE)
AS $$

	SELECT a.accountid, a.balance, b.status, b.statusid, c.type, c.typeid, a.owner, a.createdate 
	FROM account a 
	INNER JOIN accountstatus b ON a.statusid  = b.statusid 
	INNER JOIN accounttype c ON a.typeid = c.typeid 
	WHERE a.userid= id ORDER BY a.accountid DESC;

$$ LANGUAGE SQL;

SELECT * FROM get_all_accounts_by_userid(1);

/*GET ACCOUNT BY ACCOUNT ID STORED PROCEDURE */

CREATE OR REPLACE FUNCTION get_account_by_accountid(id INTEGER) 
RETURNS TABLE(accountid INTEGER, balance NUMERIC, status VARCHAR, statusid INTEGER, type VARCHAR, typeid INTEGER, owner VARCHAR, createdate DATE)
AS $$

	SELECT a.accountid, a.balance, b.status, b.statusid, c.type, c.typeid, a.owner, a.createdate 
	FROM account a 
	INNER JOIN accountstatus b ON a.statusid  = b.statusid 
	INNER JOIN accounttype c ON a.typeid = c.typeid 
	WHERE a.accountid = id;

$$ LANGUAGE SQL;

SELECT * FROM get_account_by_accountid(1);


