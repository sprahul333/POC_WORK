#language:id
Fitur: Aplikasi Perbankan

  Latar Belakang:
    Dengan Luncurkan Aplikasi

  @RegressionSuite @Banking @CreateCustomer @JIRAID_123
  Skenario konsep: Buat pelanggan baru dan verifikasi
    Dengan Navigasi ke Layar Manajer Bank
    Ketika Klik Tambah Pelanggan
    Dan Buat Pelanggan baru
      | nama_depan   | nama_belakang   | kode_pos   |
      | <nama_depan> | <nama_belakang> | <kode_pos> |
    Maka Verifikasi Pelanggan berhasil dibuat

    Contoh:
      | nama_depan | nama_belakang | kode_pos |
      | Hameed     | Joseph        | 89210215 |

  @RegressionSuite @Banking @CreateAccount @JIRAID_456
  Skenario konsep: Buat Akun Baru
    Dengan Navigasi ke Layar Manajer Bank
    Ketika Klik Tambah Pelanggan
    Dan Buat Pelanggan baru
      | nama_depan   | nama_belakang   | kode_pos   |
      | <nama_depan> | <nama_belakang> | <kode_pos> |
    Maka Verifikasi Pelanggan berhasil dibuat
    Dan Navigasi ke Layar Manajer Bank
    Ketika Klik Buka Akun
    Dan Buat Akun
      | mata_uang   |
      | <mata_uang> |
    Maka Verifikasi Akun berhasil dibuat

    Contoh:
      | nama_depan | nama_belakang | kode_pos | mata_uang |
      | Hameed     | Joseph        | 89210215 | Dollar    |