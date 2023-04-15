package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class HitController {
    private final HitService hitService;

    @PostMapping("/hit")
    public void saveHit(@RequestBody HitDtoInput hit) {
        log.info("Запись хита в базу");
        hitService.saveHit(hit);
    }

    @GetMapping("/stats")
    public List<ViewStats> getHits(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(required = false, name = "uris") List<String> uris,
            @RequestParam(required = false, defaultValue = "false", name = "unique") Boolean unique)
            throws UnsupportedEncodingException {
        log.info("Запрос статистики");
        return hitService.getStats(start, end, uris, unique);
    }
}
