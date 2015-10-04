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
