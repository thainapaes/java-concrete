package controller;

import controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

@Controller
@RequestMapping("/aplicacao/")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity access(UserDto dto) throws Exception {
        UserDto result = service.access(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("login")
    public ResponseEntity login(String email, String password) throws Exception {
        UserDto result = service.login(email, password);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity verify(@PathVariable String token, @PathVariable String id) throws Exception{
        UserDto result = service.verify(token, id);
        return ResponseEntity.ok(result);
    }


}
