package Platzi.Marke.domain.dto;

public class AuthenticationRequest {
    private String usermame;
    private String password;

    public String getUsermame() {
        return usermame;
    }

    public void setUsermame(String usermame) {
        this.usermame = usermame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
