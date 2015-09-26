CREATE TABLE movie (
    id          INTEGER PRIMARY KEY IDENTITY,
    title       LONGVARCHAR,
    description LONGVARCHAR,
    imageUrl    LONGVARCHAR,
    movieUrl     LONGVARCHAR,
    genre       LONGVARCHAR,
    year        LONGVARCHAR,
    rating      LONGVARCHAR
);