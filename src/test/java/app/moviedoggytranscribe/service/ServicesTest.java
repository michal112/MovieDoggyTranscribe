package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.exception.NoSuchEntityException;
import app.moviedoggytranscribe.exception.NoSuchMovieException;
import app.moviedoggytranscribe.model.DataSourceHolder;
import app.moviedoggytranscribe.model.DataSourceType;
import app.moviedoggytranscribe.model.dao.MovieDao;
import app.moviedoggytranscribe.model.entity.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:" + AppConstants.APPLICATION_CONTEXT_XML})
public class ServicesTest {

    @Autowired
    private Service<Movie, NoSuchMovieException> movieService;
    @Autowired
    private DataSourceHolder dataSourceHolder;

    private Logger logger = Logger.getLogger(ServicesTest.class.getName());

    @Before
    public void setUp() {
        dataSourceHolder.setDataSourceType(DataSourceType.TEST);
        logger.setLevel(Level.ALL);
    }

    @Test
    public void testMovieService() {

        Movie firstMovie = new Movie("Ojciec chrzestny",
                "Opowieść o nowojorskiej rodzinie mafijnej",
                "http://1.fwcdn.pl/po/10/89/1089/7196615.3.jpg",
                "http://www.filmweb.pl/Ojciec.Chrzestny",
                "Dramat, Gangsterski",
                "1972",
                "8.7");
        Movie secondMovie = new Movie("Zielona mila",
                "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie," +
                        "którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.",
                "http://1.fwcdn.pl/po/08/62/862/7517878.3.jpg",
                "http://www.filmweb.pl/Zielona.Mila",
                "Dramat",
                "1999",
                "8.6");

        Assert.assertTrue(movieService.add(firstMovie) != null);
        Assert.assertTrue(movieService.add(secondMovie) != null);

        try {
            Assert.assertNotEquals(movieService.get(firstMovie.getId()), movieService.get(secondMovie.getId()));
        } catch (NoSuchEntityException e) {
            logger.info("Błąd przy pobieraniu filmu, " + e.getMessage());
        }
        try {
            movieService.get(Integer.MIN_VALUE);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass().getCanonicalName(), NoSuchMovieException.class.getCanonicalName());
        }

        Assert.assertEquals(movieService.getAll().size(), 2);

        try {
            movieService.delete(firstMovie.getId());
        } catch (NoSuchEntityException e) {
            logger.info("Błąd przy pobieraniu filmu, " + e.getMessage());
        }
        Assert.assertEquals(movieService.getAll().size(), 1);
        try {
            movieService.delete(Integer.MIN_VALUE);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass().getCanonicalName(), NoSuchMovieException.class.getCanonicalName());
        }

        try {
            movieService.get(firstMovie.getId());
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass().getCanonicalName(), NoSuchMovieException.class.getCanonicalName());
        }

        secondMovie.setYear("2000");
        try {
            movieService.update(secondMovie);
        } catch (NoSuchEntityException e) {
            logger.info("Błąd przy pobieraniu filmu, " + e.getMessage());
        }
        movieService.clearEntities();
        try {
            Assert.assertTrue(movieService.get(secondMovie.getId()).getYear().equals(secondMovie.getYear()));
        } catch (NoSuchEntityException e) {
            logger.info("Błąd przy pobieraniu filmu, " + e.getMessage());
        }

    }

    @After
    public void tearDown() {
        dataSourceHolder.setDataSourceType(DataSourceType.DEFAULT);
    }

}
