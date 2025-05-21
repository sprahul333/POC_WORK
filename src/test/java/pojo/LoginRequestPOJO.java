package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestPOJO {

    private String email;
    private String password;
    private String agen;
    private int version;
}
