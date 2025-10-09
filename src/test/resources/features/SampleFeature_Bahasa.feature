#language:id
Fitur: Aplikasi Perbankan

  Latar Belakang:
    Dengan Luncurkan Aplikasi

  @RegressionSuite @Banking @CreateCustomer
  Skenario konsep: Buat Pelanggan Baru
    Dan Buat Pelanggan baru dengan Nama Depan "<first_name>" Nama Belakang "<last_name>" Kode Pos "<postal_code>"

    Contoh:
      | first_name | last_name | postal_code |
      | Hameed     | Joseph    | 89210215    |

  @RegressionSuite @Banking @CreateNewAccount
  Skenario konsep: Buat Akun Baru
    Dan Buat Pelanggan baru dengan Nama Depan "<first_name>" Nama Belakang "<last_name>" Kode Pos "<postal_code>"
    Dan Buat Akun Baru dengan Mata Uang "<Currency>"

    Contoh:
      | first_name | last_name | postal_code | Currency |
      |            |           |             |          |

  @RegressionSuite @Banking @SearchCustomers
  Skenario konsep: Cari Pelanggan
    Dan Buat Pelanggan baru dengan Nama Depan "<first_name>" Nama Belakang "<last_name>" Kode Pos "<postal_code>"
    Dan Cari Pelanggan

    Contoh:
      | first_name | last_name | postal_code |
      |            |           |             |

  @RegressionSuite @Customer @DepositAmount
  Skenario konsep: Setor Uang
    Dan Buat Pelanggan baru dengan Nama Depan "<first_name>" Nama Belakang "<last_name>" Kode Pos "<postal_code>"
    Dan Buat Akun Baru dengan Mata Uang "<Currency>"
    Dan Setor Uang sebesar "<Amount>"

    Contoh:
      | first_name | last_name | postal_code | Currency | Amount |
      |            |           |             |          |        |

  @RegressionSuite @Customer @WithdrawAmount
  Skenario konsep: Tarik Uang
    Dan Buat Pelanggan baru dengan Nama Depan "<first_name>" Nama Belakang "<last_name>" Kode Pos "<postal_code>"
    Dan Buat Akun Baru dengan Mata Uang "<Currency>"
    Dan Tarik Uang sebesar "<Amount>"

    Contoh:
      | first_name | last_name | postal_code | Currency | Amount |
      |            |           |             |          |        |