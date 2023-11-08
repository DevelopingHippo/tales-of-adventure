use toa;

CREATE table npc (
	npcName VARCHAR(50) NOT NULL UNIQUE,
    npcType VARCHAR(25) NOT NULL UNIQUE,
    
    primary key (npcName)
);

CREATE TABLE npcData (
	npcName VARCHAR(50) NOT NULL,
    
    foreign key (npcName) REFERENCES npc(npcName) ON DELETE CASCADE
);
