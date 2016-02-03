package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.constants.AppConstants;
import app.moviedoggytranscribe.model.DataSourceHolder;
import app.moviedoggytranscribe.model.DataSourceType;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:" + AppConstants.APPLICATION_CONTEXT_XML})
public class EmptyTest {

    @Autowired
    private DataSourceHolder dataSourceHolder;

    private static Logger logger = Logger.getLogger(EmptyTest.class.getName());

    @Before
    public void setUp() throws NoSuchFieldException {
        if (!dataSourceHolder.getDataSourceType().equals(DataSourceType.TEST)) {
            dataSourceHolder.setDataSourceType(DataSourceType.TEST);
        }
    }

    @After
    public void tearDown() {
        dataSourceHolder.setDataSourceType(DataSourceType.DEFAULT);
    }

}
