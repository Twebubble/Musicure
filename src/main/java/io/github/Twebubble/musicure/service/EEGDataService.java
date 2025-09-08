package io.github.Twebubble.musicure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.model.domain.EEGData;

public interface EEGDataService extends IService<EEGData> {
    R getEEGDataByUserId(Integer userId, Integer dataPoints);
}
