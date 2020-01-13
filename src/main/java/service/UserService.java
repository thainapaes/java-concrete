package service;

import controller.dto.PhoneDto;
import controller.dto.UserDto;
import entity.Phone;
import entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import repository.UserRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class UserService {

    private UserRepository repository;
    private TokenService tokenService = new TokenService();

    public UserService(UserRepository repository, TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public UserDto access(UserDto dto) throws  Exception{
        UserDto returnDto = new UserDto();

        User user = this.repository.findByEmail(dto.getEmail());
        if(user != null) {
            throw new Exception("E-mail já existente");
        }

        if(dto.getPassword().isEmpty()) {
            throw new Exception("Senha não pode ser vazia");
        }

        User userN = new User();
        userN.setName(dto.getName());
        userN.setEmail(dto.getEmail());
        userN.setPassword(dto.getPassword());

        List<Phone> phoneList = new ArrayList<>();
        for (PhoneDto phoneDto: dto.getPhonesDtoList()) {
            Phone p = new Phone();
            p.setNumber(phoneDto.getNumber());
            p.setDd(phoneDto.getDd());
            phoneList.add(p);
        }
        userN.setPhoneList(phoneList);

        LocalDateTime created = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        userN.setCreated(created);
        userN.setLastLogin(created);
        userN.setModified(created);

        this.repository.save(userN);

        String token = this.tokenService.createJWT(userN.getId().toString());
        userN.setToken(token);

        this.repository.save(userN);

        returnDto.setCreated(userN.getCreated());
        returnDto.setModifided(userN.getModified());
        returnDto.setLast_login(userN.getLastLogin());
        returnDto.setToken(userN.getToken());
        return returnDto;
    }

    public UserDto login(String email, String password) throws Exception {
        UserDto dto = new UserDto();
        User user = repository.findByEmail(email);

        if(user == null) {
            throw new Exception("Usuário e/ou senha inválidos");
        }

        if(!user.getPassword().equals(password)) {
            throw new Exception("Usuário e/ou senha inválidos");
        }

        LocalDateTime ll = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        user.setLastLogin(ll);

        this.repository.save(user);

        dto.setCreated(user.getCreated());
        dto.setModifided(user.getModified());
        dto.setLast_login(user.getLastLogin());
        dto.setToken(user.getToken());

        return dto;
    }

    public UserDto verify(String token, String id) throws Exception {
        Claims claims = this.tokenService.decodeJWT(token);
        User u = repository.findById(id);
        UserDto dto = new UserDto();

        if(claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
            throw new Exception("Sessão Invalida");
        } else {
            if(!u.getToken().equals(token)) {
                throw new Exception("Não autorizado");
            }
        }

        u.setLastLogin(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        u.setModified(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        u.setToken(this.tokenService.createJWT(id));
        this.repository.save(u);

        dto.setCreated(u.getCreated());
        dto.setModifided(u.getModified());
        dto.setLast_login(u.getLastLogin());
        dto.setToken(u.getToken());

        return dto;
    }


}
