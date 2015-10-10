PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE "status" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "name" TEXT,
    "colour" TEXT
);
INSERT INTO "status" VALUES(1,'Mam na dysku - do obejrzenia','yellow');
INSERT INTO "status" VALUES(2,'Mniej znane filmy - już dostępne w internecie','green');
INSERT INTO "status" VALUES(3,'Znane filmy kinowe','blue');
INSERT INTO "status" VALUES(4,'Filmy warte uwagi - dopiero się pojawią w kinie/internecie','purple');
INSERT INTO "status" VALUES(5,'Obejrzane','red');
CREATE TABLE "watcher" (
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "nick" TEXT,
    "name" TEXT,
    "surname" TEXT
);
INSERT INTO "watcher" VALUES(1,'MJ','Monika','Wojciechowska');
INSERT INTO "watcher" VALUES(2,'S','Paweł','Wojciechowski');
INSERT INTO "watcher" VALUES(3,'GW','Grzegorz','Wojciechowski');
INSERT INTO "watcher" VALUES(4,'DK','Dawid','Koscielski');
INSERT INTO "watcher" VALUES(5,'PW','Paweł','Wieszczeczynski');
INSERT INTO "watcher" VALUES(6,'JP','Joanna','Pajak');
INSERT INTO "watcher" VALUES(7,'DJ','Dominika','Jankowska');
INSERT INTO "watcher" VALUES(8,'Mag','Magdalena','Jendryczka');
CREATE TABLE movie (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, imageUrl TEXT, movieUrl TEXT, genre TEXT, year TEXT, rating TEXT);
INSERT INTO "movie" VALUES(1,'Ojciec chrzestny','Opowieść o nowojorskiej rodzinie mafijnej','http://1.fwcdn.pl/po/10/89/1089/7196615.3.jpg','http://www.filmweb.pl/Ojciec.Chrzestny','Dramat, Gangsterski','1972','8.7');
INSERT INTO "movie" VALUES(2,'Zielona mila','Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie, którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.','http://1.fwcdn.pl/po/08/62/862/7517878.3.jpg','http://www.filmweb.pl/Zielona.Mila','Dramat','1999','8.6');
CREATE TABLE movie_status (id INTEGER PRIMARY KEY AUTOINCREMENT, idMovie INTEGER REFERENCES movie (id), idStatus INTEGER REFERENCES status (id));
INSERT INTO "movie_status" VALUES(1,1,1);
INSERT INTO "movie_status" VALUES(2,1,2);
INSERT INTO "movie_status" VALUES(3,1,3);
INSERT INTO "movie_status" VALUES(4,2,4);
INSERT INTO "movie_status" VALUES(5,2,5);
CREATE TABLE movie_watcher (id INTEGER PRIMARY KEY AUTOINCREMENT, idMovie INTEGER REFERENCES movie (id), idWatcher INTEGER REFERENCES watcher (id));
INSERT INTO "movie_watcher" VALUES(1,1,1);
INSERT INTO "movie_watcher" VALUES(2,1,2);
INSERT INTO "movie_watcher" VALUES(3,1,3);
INSERT INTO "movie_watcher" VALUES(4,1,4);
INSERT INTO "movie_watcher" VALUES(5,2,5);
INSERT INTO "movie_watcher" VALUES(6,2,6);
INSERT INTO "movie_watcher" VALUES(7,2,7);
INSERT INTO "movie_watcher" VALUES(8,2,8);
DELETE FROM sqlite_sequence;
INSERT INTO "sqlite_sequence" VALUES('movie_status',5);
INSERT INTO "sqlite_sequence" VALUES('movie_watcher',8);
COMMIT;
