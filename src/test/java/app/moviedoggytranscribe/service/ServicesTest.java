package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.exception.*;
import app.moviedoggytranscribe.model.DataSourceHolder;
import app.moviedoggytranscribe.model.DataSourceType;
import app.moviedoggytranscribe.model.entity.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:" + AppConstants.APPLICATION_CONTEXT_XML})
public class ServicesTest {

    @Autowired
    private AbstractService<Movie, NoSuchMovieException> movieService;
    @Autowired
    private AbstractService<Status, NoSuchStatusException> statusService;
    @Autowired
    private AbstractService<Watcher, NoSuchWatcherException> watcherService;
    @Autowired
    private AbstractService<MovieStatus, NoSuchConnectionException> movieStatusService;
    @Autowired
    private AbstractService<MovieWatcher, NoSuchConnectionException> movieWatcherService;

    @Autowired
    private DataSourceHolder dataSourceHolder;

    private Logger logger = Logger.getLogger(ServicesTest.class.getName());

    private List<Movie> movies;
    private List<Status> statuses;
    private List<Watcher> watchers;
    private List<MovieStatus> movieStatusList;
    private List<MovieWatcher> movieWatcherList;

    @Before
    public void setUp() throws NoSuchFieldException {
        if (!dataSourceHolder.getDataSourceType().equals(DataSourceType.TEST)) {
            dataSourceHolder.setDataSourceType(DataSourceType.TEST);
        }

        logger.setLevel(Level.ALL);
    }

    @Test
    public void testServices() throws NoSuchMovieException, IllegalAccessException, NoSuchMethodException,
            NoSuchWatcherException, NoSuchStatusException, NoSuchConnectionException {
        initMovies();
        testCreate(movieService, movies);
        testRead(movieService, movies);
        testUpdate(movieService, movies);
        testDelete(movieService, movies);

        initStatuses();
        testCreate(statusService, statuses);
        testRead(statusService, statuses);
        testUpdate(statusService, statuses);
        testDelete(statusService, statuses);

        initWatchers();
        testCreate(watcherService, watchers);
        testRead(watcherService, watchers);
        testUpdate(watcherService, watchers);
        testDelete(watcherService, watchers);

        initMovieStatusList();
        testCreate(movieStatusService, movieStatusList);

        Assert.assertEquals(((SimpleMovieService) movieService).getMovieStasuses(movies.get(0)).size(), 2);
        movieStatusService.add(movieStatusList.get(2));
        Assert.assertEquals(((SimpleMovieService) movieService).getMovieStasuses(movies.get(1)).size(), 1);
        movieStatusService.delete(movieStatusList.get(2).getId());

        testRead(movieStatusService, movieStatusList);
        testDelete(movieStatusService, movieStatusList);

        movieService.delete(movies.get(0).getId());
        movieService.delete(movies.get(1).getId());
        statusService.delete(statuses.get(0).getId());
        statusService.delete(statuses.get(1).getId());

        initMovieWatcherList();
        testCreate(movieWatcherService, movieWatcherList);

        Assert.assertEquals(((SimpleMovieService) movieService).getMovieWatchers(movies.get(0)).size(), 2);
        movieWatcherService.add(movieWatcherList.get(2));
        Assert.assertEquals(((SimpleMovieService) movieService).getMovieWatchers(movies.get(1)).size(), 1);
        movieWatcherService.delete(movieWatcherList.get(2).getId());

        testRead(movieWatcherService, movieWatcherList);
        testDelete(movieWatcherService, movieWatcherList);

        movieService.delete(movies.get(0).getId());
        movieService.delete(movies.get(1).getId());
        watcherService.delete(watchers.get(0).getId());
        watcherService.delete(watchers.get(1).getId());
    }

    private <T extends Entity, E extends NoSuchEntityException>
            void testCreate(Service<T, E> service, List<T> entities) throws E {
        T firstEntity = entities.get(0);
        T secondEntity = entities.get(1);

        Assert.assertTrue(service.add(firstEntity) != null);
        Assert.assertTrue(service.add(secondEntity) != null);

        Assert.assertEquals(service.getAll().size(), 2);
        service.clearEntities();
        Assert.assertEquals(service.getAll().size(), 2);

        Assert.assertNotEquals(service.get(firstEntity.getId()), service.get(secondEntity.getId()));
        service.clearEntities();
        Assert.assertNotEquals(service.get(firstEntity.getId()), service.get(secondEntity.getId()));
    }

    private <T extends Entity, E extends NoSuchEntityException>
            void testRead(Service<T, E> service, List<T> entities)
                    throws E, IllegalAccessException, NoSuchMethodException {
        T firstEntity = entities.get(0);

        service.clearEntities();
        Entity entity = service.get(firstEntity.getId());

        for (Field field : Arrays.asList(entity.getClass().getDeclaredFields())) {
            field.setAccessible(true);
            Assert.assertEquals(field.get(entity), field.get(firstEntity));
        }

        service.clearEntities();
        try {
            service.get(Integer.MIN_VALUE);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass(), ResolvableType
                    .forInstance(service).getSuperType().getGeneric(1).resolve());
        }
    }

    private <T extends Entity, E extends NoSuchEntityException>
            void testUpdate(Service<T, E> service, List<T> entities) throws E, IllegalAccessException {
        T firstEntity = entities.get(0);

        service.clearEntities();
        Entity entity = service.get(firstEntity.getId());

        service.clearEntities();

        Field changedField = null;
        for (Field field : Arrays.asList(entity.getClass().getDeclaredFields())) {
            if (field.getType().equals(String.class)) {
                changedField = field;
                break;
            }
        }
        changedField.setAccessible(true);
        changedField.set(firstEntity, "TEST_VALUE");

        service.update(firstEntity);

        Assert.assertTrue(changedField.get(service.get(firstEntity.getId()))
                .equals(changedField.get(firstEntity)));
        service.clearEntities();
        Assert.assertTrue(changedField.get(service.get(firstEntity.getId()))
                .equals(changedField.get(firstEntity)));

        T secondEntity = entities.get(1);
        Integer oldId = secondEntity.getId();

        secondEntity.setId(Integer.MIN_VALUE);

        try {
            service.update(secondEntity);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass(), ResolvableType
                    .forInstance(service).getSuperType().getGeneric(1).resolve());
        }
        service.clearEntities();
        try {
            service.update(secondEntity);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass(), ResolvableType
                    .forInstance(service).getSuperType().getGeneric(1).resolve());
        }

        secondEntity.setId(oldId);
    }

    private <T extends Entity, E extends NoSuchEntityException>
            void testDelete(Service<T, E> service, List<T> entities) throws E {
        T firstEntity = entities.get(0);
        T secondEntity = entities.get(1);

        service.delete(firstEntity.getId());

        Assert.assertEquals(service.getAll().size(), 1);
        service.clearEntities();
        Assert.assertEquals(service.getAll().size(), 1);

        try {
            service.delete(Integer.MIN_VALUE);
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass(), ResolvableType
                    .forInstance(service).getSuperType().getGeneric(1).resolve());
        }
        service.clearEntities();

        try {
            service.get(firstEntity.getId());
        } catch (NoSuchEntityException e) {
            Assert.assertEquals(e.getClass(), ResolvableType
                    .forInstance(service).getSuperType().getGeneric(1).resolve());
        }

        service.delete(secondEntity.getId());

        Assert.assertEquals(service.getAll().size(), 0);
        service.clearEntities();
        Assert.assertEquals(service.getAll().size(), 0);
    }

    private void initMovies() {
        movies = new ArrayList<>();

        movies.add(new Movie("Ojciec chrzestny",
                "Opowieść o nowojorskiej rodzinie mafijnej",
                "http://1.fwcdn.pl/po/10/89/1089/7196615.3.jpg",
                "http://www.filmweb.pl/Ojciec.Chrzestny",
                "Dramat, Gangsterski",
                "1972",
                "8.7"));
        movies.add(new Movie("Zielona mila",
                "Emerytowany strażnik więzienny opowiada przyjaciółce o niezwykłym mężczyźnie," +
                        "którego skazano na śmierć za zabójstwo dwóch 9-letnich dziewczynek.",
                "http://1.fwcdn.pl/po/08/62/862/7517878.3.jpg",
                "http://www.filmweb.pl/Zielona.Mila",
                "Dramat",
                "1999",
                "8.6"));
    }

    private void initStatuses() {
        statuses = new ArrayList<>();

        statuses.add(new Status("Mam na dysku - do obejrzenia", "yellow"));
        statuses.add(new Status("Mniej znane filmy - już dostępne w internecie", "green"));
    }

    private void initWatchers() {
        watchers = new ArrayList<>();

        watchers.add(new Watcher("MJ", "Monika", "Wojciechowska"));
        watchers.add(new Watcher("S", "Paweł", "Wojciechowski"));
    }

    private void initMovieStatusList() {
        initMovies();
        initStatuses();

        movieStatusList = new ArrayList<>();

        movieService.add(movies.get(0));
        movieService.add(movies.get(1));
        statusService.add(statuses.get(0));
        statusService.add(statuses.get(1));

        movieStatusList.add(new MovieStatus(movies.get(0).getId(), statuses.get(0).getId()));
        movieStatusList.add(new MovieStatus(movies.get(0).getId(), statuses.get(1).getId()));
        movieStatusList.add(new MovieStatus(movies.get(1).getId(), statuses.get(1).getId()));
    }

    private void initMovieWatcherList() {
        initMovies();
        initWatchers();

        movieWatcherList = new ArrayList<>();

        movieService.add(movies.get(0));
        movieService.add(movies.get(1));
        watcherService.add(watchers.get(0));
        watcherService.add(watchers.get(1));

        movieWatcherList.add(new MovieWatcher(movies.get(0).getId(), watchers.get(0).getId()));
        movieWatcherList.add(new MovieWatcher(movies.get(0).getId(), watchers.get(1).getId()));
        movieWatcherList.add(new MovieWatcher(movies.get(1).getId(), watchers.get(1).getId()));
    }

    @After
    public void tearDown() {
        dataSourceHolder.setDataSourceType(DataSourceType.DEFAULT);
    }

}
