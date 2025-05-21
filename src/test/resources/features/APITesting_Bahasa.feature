#language: id
Fitur: Pengujian API Alur Ujung ke Ujung

  Skenario konsep: Mengotomatisasi Alur Ujung ke Ujung
    Dengan Pengguna melakukan login ke aplikasi
    Ketika Saya melakukan permintaan data dengan rincian berikut
      | domisili        | 1                         |
      | nama            | REYNALDI PRAMA OCTAVIALLY |
      | no_hp           | 082216805580              |
      | tanggal_lahir   | 1998-10-18                |
      | tempat_lahir    | Jakarta                   |
      | jenis_kelamin   | L                         |
      | status_kawin    | 1                         |
      | kode_kelurahan  | 11010101                  |
      | jalan           | Jalan Laksamana 92 A      |
      | ibu_kandung     | LILIS LISNAWATI           |
      | kewarganegaraan | 1                         |
      | jenis_identitas | 10                        |
      | no_identitas    | 3210201810980021          |
      | kode_cabang     | 12321                     |
      | amount          | 81234                     |
      | userFile        | logo.jpg                  |
    Dan Saya membuat VA dengan rincian berikut
      | id_transaksi | "<id_transaksi>" |
      | payment      | "<payment>"      |
      | flag         | "<flag>"         |
    Dan Saya juga memeriksa pembayaran dengan rincian berikut
      | terminalid | "<Terminal_ID>" |
    Maka Saya melakukan pembayaran dengan data transaksi berikut
      | amount     | "<Amount>" |
      | biller     | "<Biller>" |

    Contoh:
      | Terminal_ID | Amount | Biller | id_transaksi      | payment | flag |
      | 1234567890  | 81234  | 123450 | 17476224858570110 | VA_BRI  | K    |
