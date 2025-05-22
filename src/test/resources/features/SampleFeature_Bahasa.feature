#language: id
Fitur: Pengajuan Produk (Non Gadai)

  Latar Belakang:
    Dengan Account Officer sudah login

  @RegressionSuite @CreateLoanApplication @Indonesia
  Skenario konsep: Pengajuan produk <produk> berhasil
    Dengan Account Officer berada di halaman Pengajuan Non Gadai
    Dan Account Officer mengisi data pengajuan dengan outlet pencairan "<outlet>", tujuan pengajuan "<tujuanPengajuan>", tujuan kredit "<tujuanKredit>", rubrik "<rubrik>", jumlah pinjaman "<jumlahPinjaman>", dan produk "<produk>"
    Dan Account Officer mengisi data nasabah dengan NIK "<nik>", nama nasabah "<namaNasabah>", jenis kelamin "<jenisKelamin>", tempat lahir "<tempatLahir>", kode pos "<kodePos>", kelurahan "<kelurahan>" dan melakukan verifikasi dari Dukcapil
    Dan Account Officer menambahkan satu jaminan dengan kategori jaminan "<kategoriJaminan>", tipe jaminan "<tipeJaminan>", kondisi jaminan "<kondisiJaminan>"
    Ketika Account Officer menekan tombol ajukan untuk produk "<produk>"
    Maka pengajuan berhasil

    Contoh:
      | outlet          | tujuanPengajuan | tujuanKredit | rubrik    | jumlahPinjaman | produk | nik              | namaNasabah | jenisKelamin | tempatLahir | kodePos | kelurahan | kategoriJaminan | tipeJaminan  | kondisiJaminan |
      | CPS KRAMAT RAYA | PRODUKTIF       | INVESTASI    | KENDARAAN | 32000000       | AMANAH | 3577011610960002 | Automation  | Perempuan    | BANDUNG     | 63117   | KLEGEN    | KENDARAAN       | SEPEDA MOTOR | BEKAS          |
