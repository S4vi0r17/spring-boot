package med.voll.api.domain.user;

public record AuthenticateUser(
        String username,
        String password
) { }
