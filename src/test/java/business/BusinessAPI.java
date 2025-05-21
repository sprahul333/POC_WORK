package business;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.ReusableLibrary;
import framework.constants.LogStatus;
import framework.constants.StatusCodes;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import pojo.*;
import specBuilders.RequestSpecificationBuilders;
import specBuilders.ResponseSpecificationBuilders;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class BusinessAPI extends ReusableLibrary {

    String apiToken;
    RequestSpecificationBuilders specBuilders=new RequestSpecificationBuilders();
    ResponseSpecificationBuilders responseSpecificationBuilders=new ResponseSpecificationBuilders();
    InquirePaymentPOJO inquirePaymentPOJO;

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

        reports.logReportsToTheFile(LogStatus.INFO,"Request sent is: "+formParams);

        apiToken=given()
                .spec(specBuilders.getRequestSpecification_Login())
                .formParams(formParams)
        .when()
                .post("/auth/login/new")
        .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response().jsonPath().getString("token");

        reports.logReportsToTheFile(LogStatus.INFO,"Token Generated is: "+apiToken);
    }

    public void makeAnInquiry(DataTable dataTable)
    {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        MakeAnInquiryRequestPOJO inquiryRequestPOJO= MakeAnInquiryRequestPOJO.builder()
                .domisili(Integer.parseInt(data.get("domisili")))
                .nama(data.get("nama"))
                .no_hp(data.get("no_hp"))
                .tanggal_lahir(data.get("tanggal_lahir"))
                .tempat_lahir(data.get("tempat_lahir"))
                .jenis_kelamin(data.get("jenis_kelamin"))
                .status_kawin(Integer.parseInt(data.get("status_kawin")))
                .kode_kelurahan(data.get("kode_kelurahan"))
                .jalan(data.get("jalan"))
                .ibu_kandung(data.get("ibu_kandung"))
                .kewarganegaraan(Integer.parseInt(data.get("kewarganegaraan")))
                .jenis_identitas(Integer.parseInt(data.get("jenis_identitas")))
                .no_identitas(data.get("no_identitas"))
                .kode_cabang(data.get("kode_cabang"))
                .amount(data.get("amount"))
                .userFile(new File("logo.jpg"))
                .build();

        reports.logReportsToTheFile(LogStatus.INFO,"Request sent is: "+inquiryRequestPOJO);

        Response response=given()
                .spec(specBuilders.getRequestSpecification_makeAnInquiry(apiToken,inquiryRequestPOJO))
                .when()
                .post("/emas/open/inquiry")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());

        reports.logReportsToTheFile(LogStatus.INFO,"Response is: "+response.getBody().asString());
    }

    public void createVA(DataTable dataTable)
    {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        CreateVAPOJO createVAPOJO= CreateVAPOJO.builder()
                .id_transaksi(data.get("id_transaksi"))
                .payment(data.get("payment"))
                .flag(data.get("flag"))
                .build();

        ObjectMapper objectMapper=new ObjectMapper();
        Map<String, String> formParams=objectMapper.convertValue(createVAPOJO,new TypeReference<>() {});

        reports.logReportsToTheFile(LogStatus.INFO,"Request sent is: "+formParams);

        Response response=given()
                .spec(specBuilders.getRequestSpecification_CreateVA(apiToken))
                .formParams(formParams)
                .when()
                .post("/emas/open_v2/payment")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());

        reports.logReportsToTheFile(LogStatus.INFO,"Response is: "+response.getBody().asString());
    }

    @SneakyThrows
    public void inquirePayment(DataTable dataTable)
    {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        inquirePaymentPOJO=InquirePaymentPOJO.builder().terminalId(data.get("terminalid")).vaNumber("282638082216805580").build();

        //Converting object to JSON
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(inquirePaymentPOJO);

        reports.logReportsToTheFile(LogStatus.INFO,"Request sent is: "+json);

        Response response=given()
                .spec(specBuilders.getRequestSpecification_InquirePayment(inquirePaymentPOJO))
                .when()
                .post("/billing/sb/inquiry")
                .then()
                .spec(responseSpecificationBuilders.getResponseSpecification(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());

        reports.logReportsToTheFile(LogStatus.INFO,"Response is: "+response.getBody().asString());
    }

    @SneakyThrows
    public void makePayment(DataTable dataTable)
    {
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        MakePaymentPOJO makePaymentPOJO=MakePaymentPOJO.builder()
                .terminalId(inquirePaymentPOJO.getTerminalId())
                .amount(data.getOrDefault("amount","81234"))
                .vaNumber("282638082216805580")
                .reffBiller(data.getOrDefault("reffBiller","123450"))
                .build();

        //Converting object to JSON
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(makePaymentPOJO);

        reports.logReportsToTheFile(LogStatus.INFO,"Request sent is: "+json);

        Response response=given()
                .spec(specBuilders.getRequestSpecification_MakePayment(makePaymentPOJO))
                .when()
                .post("/billing/sb/payment")
                .then()
                .log().body()
                .spec(responseSpecificationBuilders.getResponseSpecification_MakePayment(StatusCodes.SUCCESS.getStatusCode()))
                .extract().response();

        System.out.println(response.getBody().asString());

        reports.logReportsToTheFile(LogStatus.INFO,"Response is: "+response.getBody().asString());
    }
}
