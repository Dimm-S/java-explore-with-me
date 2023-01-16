package ru.practicum.explore.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.user.dto.NewUserDto;
import ru.practicum.explore.user.dto.UserDto;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserAdminController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers(
            @RequestParam(required = false) List<Long> ids,
            @RequestParam(required = false, defaultValue = "0") Integer from,
            @RequestParam(required = false, defaultValue = "10") Integer size
    ) {
        log.info("Request endpoint: 'GET /admin/users' (Получение информации о пользователях)");
        return userService.getUsers(ids, from, size);
    }

    @PostMapping
    public UserDto saveUser(
            @RequestBody NewUserDto newUserDto
    ) {
        log.info("Request endpoint: 'GET /admin/users' (Добавление пользователя)");
        return userService.saveUser(newUserDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        log.info("Request endpoint: 'GET /admin/users/{}' (Удаление пользователя)", userId);
        userService.deleteUser(userId);
    }
}
