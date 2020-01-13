import controller.dto.PhoneDto;
import controller.dto.UserDto;
import entity.Phone;
import entity.User;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {


    private UserService a;

    private UserDto testUserDto;

    @Before
    public void setup(){
        testUserDto = new UserDto();
        testUserDto.setEmail("thainapaes@gmail.com");
        testUserDto.setPassword("123456");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("987652432");
        phoneDto.setDd("81");
        testUserDto.setPhonesDtoList(Arrays.asList(phoneDto));

    }

    @Test
    public void cadTest() throws Exception{
        User user = new User();
        user.setName(testUserDto.getName());
        user.setEmail(testUserDto.getEmail());
        user.setPassword(testUserDto.getPassword());
        List<Phone> phoneList = new ArrayList<>();
        for (PhoneDto dto: testUserDto.getPhonesDtoList()) {
            Phone p = new Phone();
            p.setDd(dto.getDd());
            p.setNumber(dto.getNumber());
            phoneList.add(p);
        }
        user.setPhoneList(phoneList);

        UserDto result = a.access(testUserDto);

        System.out.println(result);
    }
}
