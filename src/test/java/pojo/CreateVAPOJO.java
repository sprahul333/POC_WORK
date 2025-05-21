package pojo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateVAPOJO {

    private String id_transaksi;
    private String payment;
    private String flag;
}
