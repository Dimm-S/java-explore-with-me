package ru.practicum.mainsvc.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.mainsvc.utils.Create;
import ru.practicum.mainsvc.utils.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewCommentDto {
    @NotBlank(groups = {Create.class, Update.class})
    @NotNull(groups = {Create.class, Update.class})
    String comment;

}
