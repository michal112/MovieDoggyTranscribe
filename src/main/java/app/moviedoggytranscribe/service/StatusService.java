package app.moviedoggytranscribe.service;

import app.moviedoggytranscribe.exception.NoSuchStatusException;
import app.moviedoggytranscribe.model.entity.Status;

@org.springframework.stereotype.Service
public class StatusService extends AbstractService<Status, NoSuchStatusException> implements SimpleStatusService {}
