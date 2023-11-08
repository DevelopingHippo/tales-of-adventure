CREATE DATABASE IF NOT EXISTS toa;
CREATE DATABASE IF NOT EXISTS www;

CREATE USER 'toa'@'%' IDENTIFIED BY 'P@ssw0rd123!'; #Change this password to be randomly generated
GRANT INSERT, SELECT, UPDATE, DELETE ON toa.* TO 'toa'@'%';

CREATE USER 'web'@'%' IDENTIFIED BY 'P@ssw0rd'; #Change this password to be randomly generated
GRANT SELECT ON toa.* TO 'web'@'%';
GRANT INSERT, UPDATE, DELETE, SELECT on www.* TO 'web'@'%';

FLUSH PRIVILEGES;