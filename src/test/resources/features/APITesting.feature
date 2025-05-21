Feature: API Testing End to End Flow

  @EndToEndFlow @English @APITesting
  Scenario Outline: Automating End to End Flow
    Given User Logins to the Application
    When I Make An Enquiry with the following details
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
    And I Create a VA for the below details
      | id_transaksi | "<id_transaksi>" |
      | payment      | "<payment>"      |
      | flag         | "<flag>"         |
    And I Also Inquire for the Payments with the help of below details
      | terminalid | "<Terminal_ID>" |
    Then I Make the Payment using the given transaction details
      | amount     | "<Amount>" |
      | biller     | "<Biller>" |

    Examples:
      | Terminal_ID | Amount | Biller | id_transaksi      | payment | flag |
      | 1234567890  | 81234  | 123450 | 17476224858570110 | VA_BRI  | K    |
