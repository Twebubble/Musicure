package io.github.Twebubble.musicure.service;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.model.DataModel;

public interface MahoutDataModelService {
    DataModel buildBehaviorDataModel() throws TasteException;
}
