package stepDefs;

import business.others.BusinessAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;

public class API_Step_Defs_Bahasa 
{
    BusinessAPI businessAPI =new BusinessAPI();
    
    @Dengan("Pengguna melakukan login ke aplikasi")
    public void penggunaMelakukanLoginKeAplikasi() {
        businessAPI.loginToAPI();
    }

    @Ketika("Saya melakukan permintaan data dengan rincian berikut")
    public void sayaMelakukanPermintaanDataDenganRincianBerikut(DataTable dataTable) {
        businessAPI.makeAnInquiry(dataTable);
    }

    @Dan("Saya membuat VA dengan rincian berikut")
    public void sayaMembuatVADenganRincianBerikut(DataTable dataTable) {
        businessAPI.createVA(dataTable);
    }

    @Dan("Saya juga memeriksa pembayaran dengan rincian berikut")
    public void sayaJugaMemeriksaPembayaranDenganRincianBerikut(DataTable dataTable) {
        businessAPI.inquirePayment(dataTable);
    }

    @Maka("Saya melakukan pembayaran dengan data transaksi berikut")
    public void sayaMelakukanPembayaranDenganDataTransaksiBerikut(DataTable dataTable) {
        businessAPI.makePayment(dataTable);
    }
}
