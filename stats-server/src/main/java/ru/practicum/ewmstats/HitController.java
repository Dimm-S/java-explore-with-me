package ru.practicum.ewmstats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewmstats.dto.ViewStats;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class HitController {
    private final HitService hitService;

    @PostMapping("/hit")
    public void saveHit(@RequestBody String string) {
        log.info("Запись хита в базу");
        System.out.println(string);
        hitService.saveHit(string);
    }

    @GetMapping("/stats")
    public List<ViewStats> getHits(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end) throws UnsupportedEncodingException {
        log.info("Запрос статистики");
        return hitService.getStats(start, end);
    }
}
