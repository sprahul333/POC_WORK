package specBuilders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ResponseSpecificationBuilders {

    public ResponseSpecification getResponseSpecification(int statusCode)
    {
        ResponseSpecBuilder specBuilder=new ResponseSpecBuilder();

        specBuilder.expectStatusCode(statusCode);

        return specBuilder.build();
    }

    public ResponseSpecification getResponseSpecification_MakePayment(int statusCode)
    {
        ResponseSpecBuilder specBuilder=new ResponseSpecBuilder();

        specBuilder.expectStatusCode(statusCode)
                .expectBody("data.jenisTransaksi",equalTo("OP"));

        return specBuilder.build();
    }

    public ResponseSpecification getResponseSpecification_Login(int statusCode)
    {
        ResponseSpecBuilder specBuilder=new ResponseSpecBuilder();

        specBuilder.expectStatusCode(statusCode)
                .expectBody("token",notNullValue());

        return specBuilder.build();
    }


}
