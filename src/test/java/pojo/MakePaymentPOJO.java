package pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MakePaymentPOJO {

    private String terminalId;
    private String amount;
    private String vaNumber;
    private String reffBiller;
}
