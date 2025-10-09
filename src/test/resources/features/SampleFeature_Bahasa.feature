#language:id
Fitur: Aplikasi Perbankan

  Latar Belakang:
    Dengan Luncurkan Aplikasi

  @RegressionSuite @Banking @CreateCustomer
  Skenario konsep: Buat pelanggan baru dan verifikasi
    Dengan Navigasi ke Layar Manajer Bank
    Ketika Klik Tambah Pelanggan
    Dan Buat Pelanggan baru
      | nama_depan | nama_belakang | kode_pos  |
      | <nama_depan> | <nama_belakang> | <kode_pos> |
    Maka Verifikasi Pelanggan berhasil dibuat

    Contoh:
      | nama_depan | nama_belakang | kode_pos |
      | Hameed     | Joseph        | 89210215 |