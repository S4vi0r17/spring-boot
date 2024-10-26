package med.voll.api.domain.user;

public record AuthenticateUserDto(
        String username,
        String password
) { }
