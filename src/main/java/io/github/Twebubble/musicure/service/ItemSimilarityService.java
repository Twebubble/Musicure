package io.github.Twebubble.musicure.service;

public interface ItemSimilarityService {
    double calculateSongBehaviorSimilarity(Long songId1, Long songId2);
}
