package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.model.entity.Status;

import java.util.List;

public interface SimpleStatusService extends Service<Status, NoSuchStatusException> {
    List<String> getAllExistStatuses() throws NoSuchStatusException;
}
