package pojo;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class MakeAnInquiryRequestPOJO {

    private int domisili;
    private String nama;
    private String no_hp;
    private String tanggal_lahir;
    private String tempat_lahir;
    private String jenis_kelamin;
    private int status_kawin;
    private String kode_kelurahan;
    private String jalan;
    private String ibu_kandung;
    private int kewarganegaraan;
    private int jenis_identitas;
    private String no_identitas;
    private String kode_cabang;
    private String amount;
    private File userFile;
}
