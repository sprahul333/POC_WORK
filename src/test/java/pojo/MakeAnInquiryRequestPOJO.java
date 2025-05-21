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

    @Override
    public String toString() {
        return "{\n" +
                "  \"domisili\": " + domisili + ",\n" +
                "  \"nama\": \"" + nama + "\",\n" +
                "  \"no_hp\": \"" + no_hp + "\",\n" +
                "  \"tanggal_lahir\": \"" + tanggal_lahir + "\",\n" +
                "  \"tempat_lahir\": \"" + tempat_lahir + "\",\n" +
                "  \"jenis_kelamin\": \"" + jenis_kelamin + "\",\n" +
                "  \"status_kawin\": " + status_kawin + ",\n" +
                "  \"kode_kelurahan\": \"" + kode_kelurahan + "\",\n" +
                "  \"jalan\": \"" + jalan + "\",\n" +
                "  \"ibu_kandung\": \"" + ibu_kandung + "\",\n" +
                "  \"kewarganegaraan\": " + kewarganegaraan + ",\n" +
                "  \"jenis_identitas\": " + jenis_identitas + ",\n" +
                "  \"no_identitas\": \"" + no_identitas + "\",\n" +
                "  \"kode_cabang\": \"" + kode_cabang + "\",\n" +
                "  \"amount\": \"" + amount + "\",\n" +
                "  \"userFile\": \"" + (userFile != null ? userFile.getName() : "") + "\"\n" +
                "}";
    }
}
