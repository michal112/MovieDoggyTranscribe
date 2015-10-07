CREATE TABLE movie (
    id          INTEGER PRIMARY KEY IDENTITY,
    title       LONGVARCHAR,
    description LONGVARCHAR,
    imageUrl    LONGVARCHAR,
    movieUrl    LONGVARCHAR,
    genre       LONGVARCHAR,
    year        LONGVARCHAR,
    rating      LONGVARCHAR
);

CREATE TABLE status (
    id     INTEGER PRIMARY KEY IDENTITY,
    name   LONGVARCHAR,
    colour LONGVARCHAR
);

CREATE TABLE watcher (
    id      INTEGER PRIMARY KEY IDENTITY,
    nick    LONGVARCHAR,
    name    LONGVARCHAR,
    surname LONGVARCHAR
);

CREATE TABLE movie_status (
    id        INTEGER PRIMARY KEY IDENTITY,
    idMovie  INTEGER REFERENCES movie (id),
    idStatus INTEGER REFERENCES status (id)
);

CREATE TABLE movie_watcher (
    id         INTEGER PRIMARY KEY IDENTITY,
    idMovie   INTEGER REFERENCES movie (id),
    idWatcher INTEGER REFERENCES watcher (id)
);

