package ru.practicum.explore.user;

import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.user.dto.NewUserDto;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.dto.UserShortDto;

import java.util.List;

public interface UserService {
    UserShortDto getUser(Long id);
    List<UserDto> getUsers(List<Long> ids, Integer from, Integer size);
    UserDto saveUser(NewUserDto newUserDto);
    void deleteUser(Long userId);
}
