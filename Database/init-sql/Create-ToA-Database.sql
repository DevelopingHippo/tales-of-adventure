use toa;

CREATE TABLE log (
    logID BIGINT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    type VARCHAR(15),
    message VARCHAR(100) NOT NULL,
	primary key (logID)
);

INSERT INTO log VALUES ("1", "2021-12-13", "00:00:00", null, "First Log");