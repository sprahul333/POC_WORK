package stepDefs;

import business.BankManagerBusiness;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;

import java.util.List;
import java.util.Map;

public class BankManagerStepDefs
{
    BankManagerBusiness business = new BankManagerBusiness();

    @Given("Navigate to Bank Manager Screen")
    @Dan("Navigasi ke Layar Manajer Bank")
    public void navigateToBankManagerScreen()
    {
        business.navigateToBankManagerScreen();
    }

    @And("Create a new Customer having First Name {string} Last Name {string} Post Code {string}")
    @Dan("Buat Pelanggan baru dengan Nama Depan {string} Nama Belakang {string} Kode Pos {string}")
    public void createANewCustomerHavingFirstNameLastNamePostCode(String firstName, String lastName, String postalCode)
    {
        business.createCustomer(firstName, lastName, postalCode);
    }

    @And("Create a new Customer")
    public void createNewCustomer(DataTable dataTable)
    {
        List<Map<String,String>> dataSet=dataTable.asMaps();
        dataSet.forEach(map -> {
            business.createCustomer(map.get("first_name"), map.get("last_name"), map.get("postal_code"));
        });
    }

    @Dan("Buat Pelanggan baru")
    public void createNewCustomer_Bahas(DataTable dataTable)
    {
        List<Map<String,String>> dataSet=dataTable.asMaps();
        dataSet.forEach(map -> {
            business.createCustomer(map.get("nama_depan"), map.get("nama_belakang"), map.get("kode_pos"));
        });
    }

    @And("Create a New Account having Currency {string}")
    @Dan("Buat Akun Baru dengan Mata Uang {string}")
    public void createANewAccountHavingCurrency(String currency) {
        business.createAccounts(currency);
    }

    @And("Search for the Customer")
    @Dan("Cari Pelanggan")
    public void searchForTheCustomer() {
        business.searchForCustomer();
    }


    @When("Click On Add Customer")
    @Ketika("Klik Tambah Pelanggan")
    public void clickOnAddCustomer() {
        business.navigateToAddCustomersScreen();
    }

    @Then("Verify Customer is created successfully")
    @Maka("Verifikasi Pelanggan berhasil dibuat")
    public void verifyCustomerIsCreatedSuccessfully() {
        business.validateCustomerDetails();
    }
}
