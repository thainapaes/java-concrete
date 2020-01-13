package controller.dto;

public class PhoneDto {

    private String number;
    private String dd;
    private UserDto userDto;

    public PhoneDto(){}

    public PhoneDto(String number, String dd, UserDto userDto) {
        this.number = number;
        this.dd = dd;
        this.userDto = userDto;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
