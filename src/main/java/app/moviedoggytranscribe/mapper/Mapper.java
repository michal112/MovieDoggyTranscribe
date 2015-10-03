package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.Data;
import app.moviedoggytranscribe.model.entity.Entity;

import java.util.List;

public interface Mapper<V extends Data> {

    V mapToData(List<? extends Entity> entities);

}
