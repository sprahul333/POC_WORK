package framework;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.experimental.UtilityClass;

@UtilityClass //It marks all the methods as static in nature and prevents any object creation for the class
public class GenAIUtilities {

    public String convertDataFromOneLanguageToAnother(String message)
    {
        Client client = Client.builder().apiKey("AIzaSyDCFE__Sac7FKDNEZnjmqr5bCdxvDSG9hM").build();
        if(!ReusableLibrary.testUtilThread.get().getData("Language").isBlank())
        {
            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-2.5-flash",
                            "Convert the short form of " + System.getProperty("Language") + " into a full name and here is the gherkin link for localised language https://cucumber.io/docs/gherkin/languages and provide only the language name",
                            null);

            String fullLanguage = response.text();

            response =
                    client.models.generateContent(
                            "gemini-2.5-flash",
                            "Convert " + message + " into " + fullLanguage + " Language and display only the translated text and do not remove any HTML tags because we are using this in our report generation",
                            null);

            return response.text();
        }

        else {
            return message;
        }
    }
}