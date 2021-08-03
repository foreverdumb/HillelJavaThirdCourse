SET SCHEMA PUBLIC;

CREATE TABLE IF NOT EXISTS Countries
(
    id_country   INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    country_name VARCHAR(150)                       NOT NULL
);

CREATE TABLE IF NOT EXISTS Regions
(
    id_region   INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_con      INTEGER                            NOT NULL,
    region_name VARCHAR(150)                       NOT NULL
);

CREATE TABLE IF NOT EXISTS Cities
(
    id_city   INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_con    INTEGER                            NOT NULL,
    id_reg    INTEGER                            NOT NULL,
    city_name VARCHAR(150)                       NOT NULL
);

ALTER TABLE IF EXISTS Cities
    ADD CONSTRAINT FK_Cities_Regions
        FOREIGN KEY (id_reg)
            REFERENCES Regions (id_region)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE IF EXISTS Cities
    ADD CONSTRAINT FK_Cities_Countries
        FOREIGN KEY (id_con)
            REFERENCES Countries (id_country)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE IF EXISTS Regions
    ADD CONSTRAINT FK_Regions_Countries
        FOREIGN KEY (id_con)
            REFERENCES Countries (id_country)
            ON DELETE CASCADE
            ON UPDATE CASCADE;