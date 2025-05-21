package apiTesting;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.StatusCodes;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.*;
import specBuilders.RequestSpecificationBuilders;
import specBuilders.ResponseSpecificationBuilders;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITESTING_BKP {

    String apiToken;
    RequestSpecificationBuilders specBuilders=new RequestSpecificationBuilders();
    ResponseSpecificationBuilders responseSpecificationBuilders=new ResponseSpecificationBuilders();

    @Test(description="Login to the API",priority = 1)
    public void loginToAPI()
    {
        LoginRequestPOJO login= LoginRequestPOJO.builder()
                .agen("mobile")
                .version(3)
                .email("082216805580")
                .password("Kramat321!")
                .build();

        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, String> formParams=objectMapper.convertValue(login,new TypeReference<>() {});

        apiToken=given()
                .spec(specBuilders.getRequestSpecification_Login())
                .formParams(formParams)
        .when()
                .post("/auth/login/new")
        .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response().jsonPath().getString("token");
    }

    @Test(description="Make An Inquiry",priority = 2)
    public void makeAnInquiry()
    {
        MakeAnInquiryRequestPOJO inquiryRequestPOJO= MakeAnInquiryRequestPOJO.builder()
                .domisili(1)
                .nama("REYNALDI PRAMA OCTAVIALLY")
                .no_hp("082216805580")
                .tanggal_lahir("1998-10-18")
                .tempat_lahir("Jakarta")
                .jenis_kelamin("L")
                .status_kawin(1)
                .kode_kelurahan("11010101")
                .jalan("Jalan Laksamana 92 A")
                .ibu_kandung("LILIS LISNAWATI")
                .kewarganegaraan(1)
                .jenis_identitas(10)
                .no_identitas("3210201810980021")
                .kode_cabang("12321")
                .amount("81234")
                .userFile(new File("logo.jpg"))
                .build();

        Response response=given()
                .spec(specBuilders.getRequestSpecification_makeAnInquiry(apiToken,inquiryRequestPOJO))
                .when()
                .post("/emas/open/inquiry")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());
    }

    @Test(description="Create VA",priority = 3)
    public void createVA()
    {
        CreateVAPOJO createVAPOJO= CreateVAPOJO.builder()
                .id_transaksi("17476224858570110")
                .payment("VA_BRI")
                .flag("K")
                .build();

        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, String> formParams=objectMapper.convertValue(createVAPOJO,new TypeReference<>() {});

        Response response=given()
                .spec(specBuilders.getRequestSpecification_CreateVA(apiToken))
                .formParams(formParams)
                .when()
                .post("/emas/open_v2/payment")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());
    }

    @Test(description="Inquire Payment",priority = 4)
    public void inquirePayment()
    {

        InquirePaymentPOJO paymentPOJO=InquirePaymentPOJO.builder().terminalId("1234567890").vaNumber("282638082216805580").build();

        Response response=given()
                .spec(specBuilders.getRequestSpecification_InquirePayment(paymentPOJO))
                .when()
                .post("/billing/sb/inquiry")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());
    }

    @Test(description="Make Payment",priority = 5)
    public void makePayment()
    {

        MakePaymentPOJO makePaymentPOJO=MakePaymentPOJO.builder()
                .terminalId("1234567890")
                .amount("81234")
                .vaNumber("282638082216805580")
                .reffBiller("123450")
                .build();

        Response response=given()
                .spec(specBuilders.getRequestSpecification_MakePayment(makePaymentPOJO))
                .when()
                .post("/billing/sb/payment")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification_MakePayment(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());
    }
}
