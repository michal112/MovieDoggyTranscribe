package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.StatusData;
import app.moviedoggytranscribe.model.entity.Status;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToStatusDataMapper implements Mapper<Status, StatusData> {

    @Override
    public List<StatusData> mapToData(List<Status> entities) {
        List<StatusData> statusDataList = new ArrayList<>();

        entities.stream().forEach(status -> statusDataList.add(new StatusData(status)));

        return statusDataList;
    }

}
