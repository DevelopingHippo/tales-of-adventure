use toa;

# ACCOUNT INFORMATION
CREATE TABLE account (
	username VARCHAR(30) NOT NULL UNIQUE, 
	password VARCHAR(64) NOT NULL,
    primary key (username)
);

# CHARACTER INFORMATION
CREATE TABLE playerCharacter (
	username VARCHAR(30) NOT NULL, 
    name VARCHAR(50) NOT NULL UNIQUE,
    level INT NOT NULL check(level >= 0) DEFAULT 0,
    totExp int NOT NULL check(totExp >= 0) DEFAULT 0,
    worldLocation VARCHAR(50) NOT NULL DEFAULT 'Intro',
    primary key (name),
    foreign key(username) REFERENCES account(username) ON DELETE cascade
);
CREATE TABLE playerInventory (
	name VARCHAR(50) NOT NULL,
    itemName VARCHAR(30) NOT NULL,
    itemType VARCHAR(30) NOT NULL,
    foreign key (name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);

# COMBAT SKILLS
CREATE TABLE onehandedSkill (
	name VARCHAR(50) NOT NULL,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,
    
    # this is where the skills booleans will be DEFAULT false
    quickStab BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);
CREATE TABLE twohandedSkill (
	name VARCHAR(50) NOT NULL UNIQUE,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,
    
    # this is where the skills booleans will be DEFAULT false
    heavySwing BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);
CREATE TABLE rangedSkill (
	name VARCHAR(50) NOT NULL UNIQUE,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,
    
    # this is where the skills booleans will be DEFAULT false
    doubleShot BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);
CREATE TABLE magickSkill (
	name VARCHAR(50) NOT NULL UNIQUE,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,    
    
    # this is where the skills booleans will be DEFAULT false
    fireball BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);
CREATE TABLE blockSkill (
	name VARCHAR(50) NOT NULL UNIQUE,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,    
    
    # this is where the skills booleans will be DEFAULT false
    readyBlock BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);

# GATHERING SKILLS
CREATE TABLE fishingSkill (
	name VARCHAR(50) NOT NULL UNIQUE,
	level INT NOT NULL DEFAULT 0,
    totExp INT NOT NULL DEFAULT 0,
    
    # this is where the skills booleans will be DEFAULT false
    freshWaterFishing BOOLEAN NOT NULL DEFAULT FALSE,
    
    primary key (name),
    foreign key(name) REFERENCES playerCharacter(name) ON DELETE CASCADE
);





INSERT INTO account VALUES ("admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
INSERT INTO account VALUES ("admin2", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
INSERT INTO account VALUES ("tgsander", "57c93f9c17fb2f1aa53d7f68bbc6feb183c64d7902b86c0f7b5eb14d2b36ed77");