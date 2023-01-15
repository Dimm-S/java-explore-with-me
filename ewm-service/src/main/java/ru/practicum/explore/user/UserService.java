package ru.practicum.explore.user;

import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.user.dto.NewUserDto;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.dto.UserShortDto;

import java.util.List;

public interface UserService {
    UserShortDto getUser(Integer id);
    List<UserDto> getUsers(List<Integer> ids, Integer from, Integer size);
    UserDto saveUser(NewUserDto newUserDto);
    void deleteUser(Integer userId);
}
