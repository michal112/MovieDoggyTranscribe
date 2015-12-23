package app.moviedoggytranscribe.mapper;

import app.moviedoggytranscribe.model.data.WatcherData;
import app.moviedoggytranscribe.model.entity.Watcher;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ToWatcherDataMapper implements Mapper<Watcher, WatcherData> {

    @Override
    public List<WatcherData> mapToData(List<Watcher> entities) {
        List<WatcherData> watcherDataList = new ArrayList<>();

        entities.stream().forEach(watcher -> watcherDataList.add(new WatcherData(watcher)));

        return watcherDataList;
    }

}
