package stepDefs;

import business.CustomerBusiness;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.id.Dan;

public class CustomerStepDefs {

    CustomerBusiness customerBusiness = new CustomerBusiness();

    @And("Deposit the Amount of {string}")
    @Dan("Setor Uang sebesar {string}")
    public void depositTheAmountOf(String amount) {
        customerBusiness.performDeposits(amount);
    }

    @And("Withdraw the Amount of {string}")
    @Dan("Tarik Uang sebesar {string}")
    public void withdrawTheAmountOf(String amount) {
        customerBusiness.performWithdrawal(amount);
    }
}
