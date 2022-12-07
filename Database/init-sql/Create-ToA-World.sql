use toa;

DROP TABLE cityData;
DROP TABLE townData;

DROP TABLE worldLocation;


CREATE TABLE worldLocation (
	locationName VARCHAR(50) NOT NULL,
    locationType VARCHAR(25) NOT NULL,
    
    primary key (locationName)
);

CREATE TABLE cityData (
	cityName VARCHAR(50) NOT NULL,
    
    foreign key (cityName) REFERENCES worldLocation(locationName) ON DELETE CASCADE
);

CREATE TABLE townData (
	townName VARCHAR(50) NOT NULL,
    
    foreign key (townName) REFERENCES worldLocation (locationName) ON DELETE CASCADE
);