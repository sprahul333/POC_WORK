package pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InquirePaymentPOJO {

    private String terminalId;
    private String vaNumber;
}
