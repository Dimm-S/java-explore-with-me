package ru.practicum;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface HitService {
    void saveHit(String string);

    List<ViewStats> getStats(String start, String end) throws UnsupportedEncodingException;
}
