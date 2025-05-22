package stepDefs;

import business.BusinessComponents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;

public class ProductStepDefs_Bahasa 
{
    BusinessComponents businessComponents = new BusinessComponents();
    String referenceNumber="";

    @Dengan("Account Officer sudah login")
    public void accountOfficerSudahLogin() {
        businessComponents.loginToApplication();
    }

    @Dengan("Account Officer berada di halaman Pengajuan Non Gadai")
    public void accountOfficerBeradaDiHalamanPengajuanNonGadai() {
        businessComponents.navigateToNonPawnApplication();
    }

    @Dan("Account Officer mengisi data pengajuan dengan outlet pencairan {string}, tujuan pengajuan {string}, tujuan kredit {string}, rubrik {string}, jumlah pinjaman {string}, dan produk {string}")
    public void accountOfficerMengisiDataPengajuan(String outlet, String tujuanPengajuan, String tujuanKredit, String rubrik, String jumlahPinjaman, String produk) {
        businessComponents.fillApplicationData(outlet, tujuanPengajuan, tujuanKredit, rubrik, jumlahPinjaman, produk);
    }

    @Dan("Account Officer mengisi data nasabah dengan NIK {string}, nama nasabah {string}, jenis kelamin {string}, tempat lahir {string}, kode pos {string}, kelurahan {string} dan melakukan verifikasi dari Dukcapil")
    public void accountOfficerMengisiDataNasabah(String nik, String nama, String jenisKelamin, String tempatLahir, String kodePos, String kelurahan) {
        businessComponents.fillCustomerData(nik, nama, jenisKelamin, tempatLahir, kodePos, kelurahan);
    }


    @Dan("Account Officer menambahkan satu jaminan dengan kategori jaminan {string}, tipe jaminan {string}, kondisi jaminan {string}")
    public void accountOfficerMenambahkanJaminan(String kategori, String tipe, String kondisi) {
        businessComponents.enterCollateralData(kategori, tipe, kondisi);
    }

    @Ketika("Account Officer menekan tombol ajukan untuk produk {string}")
    public void accountOfficerMenekanAjukanProduk(String produk) {
        referenceNumber=businessComponents.reviewTermsAndConditionsAndData();
    }

    @Maka("pengajuan berhasil")
    public void pengajuanBerhasil() {
        System.out.println("Reference Number: "+referenceNumber);
    }

}
