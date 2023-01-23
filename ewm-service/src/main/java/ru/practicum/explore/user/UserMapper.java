package ru.practicum.explore.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.user.dto.NewUserDto;
import ru.practicum.explore.user.dto.UserDto;
import ru.practicum.explore.user.dto.UserShortDto;
import ru.practicum.explore.user.model.User;

@Service
@RequiredArgsConstructor
public class UserMapper {
    public UserShortDto mapToUserShortDto(User user) {
        return new UserShortDto(user.getId(), user.getName());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getEmail(),
                user.getId(),
                user.getName()
        );
    }

    public User mapToUser(NewUserDto userDto) {
        return new User(
                null,
                userDto.getEmail(),
                userDto.getName()
        );
    }
}
