package ru.practicum;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface HitService {
    void saveHit(HitDtoInput hit);

    List<ViewStats> getStats(String start, String end, List<String> uris, Boolean unique)
            throws UnsupportedEncodingException;
}
