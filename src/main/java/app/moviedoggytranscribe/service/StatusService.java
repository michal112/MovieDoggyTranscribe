package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.model.dao.SimpleStatusDao;
import app.moviedoggytranscribe.model.entity.Status;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class StatusService extends AbstractService<Status, NoSuchStatusException> implements SimpleStatusService {

    @Override
    public List<String> getAllExistStatuses() throws NoSuchStatusException {
        List<String> stringList = new ArrayList<String>();
        List<Status> statusList = ((SimpleStatusDao) getDao()).getAll();
        for (Status status : statusList) {
            stringList.add(status.getName());
        }

        return stringList;
    }
}
