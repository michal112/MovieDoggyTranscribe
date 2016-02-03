package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.Data;
import app.moviedoggytranscribe.model.entity.Entity;

import java.util.List;

public interface Mapper<T extends Entity, V extends Data> {

    List<V> mapToData(List<T> entities);

}
