package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.model.entity.Status;

public interface SimpleStatusService extends Service<Status, NoSuchStatusException> {}