package stepDefs;

import business.BankManagerBusiness;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.id.Dan;

public class BankManagerStepDefs
{
    BankManagerBusiness business = new BankManagerBusiness();


    @And("Create a new Customer having First Name {string} Last Name {string} Post Code {string}")
    @Dan("Buat Pelanggan baru dengan Nama Depan {string} Nama Belakang {string} Kode Pos {string}")
    public void createANewCustomerHavingFirstNameLastNamePostCode(String firstName, String lastName, String postalCode)
    {
        business.createCustomer(firstName, lastName, postalCode);
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


}
