package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.DataSourceHolder;
import app.moviedoggytranscribe.model.DataSourceType;
import app.moviedoggytranscribe.model.dao.MovieDao;
import app.moviedoggytranscribe.model.entity.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MovieDaoTest {

    MovieService movieService;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext(AppConstants.APPLICATION_CONTEXT_XML);
        movieService = context.getBean(MovieService.class);
        DataSourceHolder.setDataSourceType(DataSourceType.TEST);
    }

    @Test
    public void testMovieDao() {
        assert (movieService.add(new Movie("Ojciec chrzestny",
                "Opowieść o nowojorskiej rodzinie mafijnej",
                "http://1.fwcdn.pl/po/10/89/1089/7196615.3.jpg",
                "http://www.filmweb.pl/Ojciec.Chrzestny",
                "Dramat, Gangsterski",
                "1972",
                "8.7")) instanceof Integer);
    }

    @After
    public void tearDown() {
        DataSourceHolder.setDataSourceType(DataSourceType.DEFAULT);
    }
}
