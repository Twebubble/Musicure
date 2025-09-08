package io.github.Twebubble.musicure.controller;

import io.github.Twebubble.musicure.common.R;
import io.github.Twebubble.musicure.service.EEGDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EEGDataController {

    @Autowired
    private EEGDataService eegDataService;

    @GetMapping("/data/{userId}")
    public R getEEGData(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "300") Integer dataPoints) {

        return eegDataService.getEEGDataByUserId(userId, dataPoints);
    }
}
