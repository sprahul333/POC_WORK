package specBuilders;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.CreateVAPOJO;
import pojo.InquirePaymentPOJO;
import pojo.MakeAnInquiryRequestPOJO;
import pojo.MakePaymentPOJO;

import java.io.File;

public class RequestSpecificationBuilders {

    public RequestSpecification getRequestSpecification_Login()
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.URLENC)
            .addHeader("X-Android-Version","7.0.1")
            .addCookie("Cookie","9c192e374d912db54b16479e46b977b0=ecbf2a4646a847f11a90b61c42af64b8")
            .addFilter(new AllureRestAssured())
                .setBaseUri("https://pds-api-app-backend-pds-qa-modernisasi-digital-dev.apps.ocp-dev.pegadaian.co.id");

        return requestSpecBuilder.build();
    }

    public RequestSpecification getRequestSpecification_CreateVA(String token)
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.URLENC)
                        .addFilter(new AllureRestAssured())
                                .addHeader("Authorization",token)
                        .addHeader("Cookie","9c192e374d912db54b16479e46b977b0=ecbf2a4646a847f11a90b61c42af64b8")
                        .setBaseUri("https://pds-api-app-backend-pds-qa-modernisasi-digital-dev.apps.ocp-dev.pegadaian.co.id")
                .log(LogDetail.BODY);

        return requestSpecBuilder.build();
    }


    public RequestSpecification getRequestSpecification_InquirePayment(InquirePaymentPOJO inquirePaymentPOJO)
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization","Basic YWRtaW46YWRtaW4=")
                .addHeader("Cookie","9c192e374d912db54b16479e46b977b0=ecbf2a4646a847f11a90b61c42af64b8")
                .setBaseUri("https://billing-service-v2-switching-dev.apps.ocp-dev.pegadaian.co.id")
                .setBody(inquirePaymentPOJO)
                .log(LogDetail.BODY);

        return requestSpecBuilder.build();
    }


    public RequestSpecification getRequestSpecification_MakePayment(MakePaymentPOJO makePaymentPOJO)
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization","Basic YWRtaW46YWRtaW4=")
                .setBaseUri("https://billing-service-v2-switching-dev.apps.ocp-dev.pegadaian.co.id")
                .setBody(makePaymentPOJO).log(LogDetail.BODY);

        return requestSpecBuilder.build();
    }

    public RequestSpecification getRequestSpecification_MakePayment_ExternalFile(String filePath)
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization","Basic YWRtaW46YWRtaW4=")
                .setBaseUri("https://billing-service-v2-switching-dev.apps.ocp-dev.pegadaian.co.id")
                .setBody(new File(filePath)).log(LogDetail.BODY);

        return requestSpecBuilder.build();
    }

    public RequestSpecification getRequestSpecification_makeAnInquiry(String token, MakeAnInquiryRequestPOJO inquiryRequestPOJO)
    {
        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.MULTIPART)
                .addHeader("Cookie","9c192e374d912db54b16479e46b977b0=ecbf2a4646a847f11a90b61c42af64b8")
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization",token)
                .setBaseUri("https://pds-api-app-backend-pds-qa-modernisasi-digital-dev.apps.ocp-dev.pegadaian.co.id")
                .log(LogDetail.BODY);

        requestSpecBuilder.addMultiPart("domisili", String.valueOf(1))
                .addMultiPart("nama", "REYNALDI PRAMA OCTAVIALLY")
                .addMultiPart("no_hp", "082216805580")
                .addMultiPart("tanggal_lahir", "1998-10-18")
                .addMultiPart("tempat_lahir", "Jakarta")
                .addMultiPart("jenis_kelamin", "L")
                .addMultiPart("status_kawin", String.valueOf(1))
                .addMultiPart("kode_kelurahan", "11010101")
                .addMultiPart("jalan", "Jalan Laksamana 92 A")
                .addMultiPart("ibu_kandung", "LILIS LISNAWATI")
                .addMultiPart("kewarganegaraan", String.valueOf(1))
                .addMultiPart("jenis_identitas", String.valueOf(10))
                .addMultiPart("no_identitas", "3210201810980021")
                .addMultiPart("kode_cabang", "12321")
                .addMultiPart("amount", "81234")
                .addMultiPart("userFile", new File("logo.jpg"));

        return requestSpecBuilder.build();
    }

    public RequestSpecification getUserRegisteredOrNot(String referenceNumber)
    {

        RequestSpecBuilder requestSpecBuilder=new RequestSpecBuilder();

        requestSpecBuilder.setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization","Basic YWRtaW46YWRtaW4=")
                .setBaseUri("https://loan-pengajuan-los-dev.apps.ocp-dev.pegadaian.co.id")
                .addQueryParam("nomorAplikasi",referenceNumber).log(LogDetail.BODY);

        return requestSpecBuilder.build();
    }
}
