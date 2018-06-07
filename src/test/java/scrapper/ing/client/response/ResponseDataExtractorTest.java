package scrapper.ing.client.response;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import scrapper.ing.account.IngAccountInfo;
import scrapper.ing.security.AuthenticatedSession;
import scrapper.ing.security.PasswordMetadata;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ResponseDataExtractorTest {

    private ResponseDataExtractor testedService = new ResponseDataExtractor();

    @Test
    public void shouldReturnEmptyIfIncorrectResponseData() throws JSONException {
        // given
        Response response = new Response(new JSONObject("{}"), new Header[]{});

        // when
        List<IngAccountInfo> accountInfos = this.testedService.extractAccountsInfo(response);
        AuthenticatedSession authenticatedSession = this.testedService.extractAuthenticatedSession(response);
        PasswordMetadata passwordMetadata = this.testedService.extractPasswordMetadata(response);

        // then
        assertTrue(accountInfos.isEmpty());
        assertTrue(authenticatedSession.isEmpty());
        assertTrue(passwordMetadata.isEmpty());
    }

    // Tests below would be way better if they could work on actual responses not mocks
    @Test
    public void shouldReturnAccountInfoOnProperResponse() throws JSONException {
        // given
        String jsonAsString = "{\"data\":{\"sav\":[],\"cur\":[]}}";
        Response response = new Response(new JSONObject(jsonAsString), new Header[]{});

        // when
        List<IngAccountInfo> result = this.testedService.extractAccountsInfo(response);

        // then
        assertTrue(result.isEmpty());

    }
}