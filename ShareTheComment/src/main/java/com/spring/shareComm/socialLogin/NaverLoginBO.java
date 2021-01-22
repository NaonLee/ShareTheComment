package com.spring.shareComm.socialLogin;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

//create request sentence
public class NaverLoginBO {

	/* Parameter for authentication request sentence
	 * client_id: the client id after application registration 
	 * response_type: divider for authentication process
	 * state: status token created by application
	 */
	
	private final static String CLIENT_ID = "Jm5IJ3hUXN5CMdVdMQ4C";
	private final static String CLIENT_SECRET = "SDYOxFenZc";
	private final static String REDIRECT_URI = "http://localhost:8090/shareComm/member/naverLogin.do";
	private final static String SESSION_STATE = "oauth_state";
	
	//API URL for inquiry profile
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	//Method: Create authentication URL by Naver id
	public String getAuthenticationUrl(HttpSession session) {
		//Random number for session validation
		String state = generateRandomString();
		
		//Save random number to session
		setSession(session, state);
		//Create 네아로 authentication by URI creator provided by Scribe
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID)
															.apiSecret(CLIENT_SECRET)
															.callback(REDIRECT_URI)
															.state(state)				//Use random number created before
															.build(NaverLoginApi.instance());
		
		return oauthService.getAuthorizationUrl();
	}
	
	//Callback and obtaining AccessToken by Naver id
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state) throws IOException {
		//Check if random number and value in session are in match
		String sessionState =  getSession(session);
		
		if(StringUtils.pathEquals(sessionState, state)) {
			OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID)
																.apiSecret(CLIENT_SECRET)
																.callback(REDIRECT_URI)
																.state(state)
																.build(NaverLoginApi.instance());
			
			//Get 네아로 access token by access token function provided by Scribe
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}
	
	//Random number creator
	private String generateRandomString() {
		return UUID.randomUUID().toString();
	}
	
	//Save data on httpSession
	private void setSession(HttpSession session, String state) {
		session.setAttribute(SESSION_STATE, state);
	}
	
	//Get data from httpSession
	private String getSession(HttpSession session) {
		return (String) session.getAttribute(SESSION_STATE);
	}
	
	//Call Naver profile API using access token
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		OAuth20Service oauthService = new ServiceBuilder().apiKey(CLIENT_ID)
															.apiSecret(CLIENT_SECRET)
															.callback(REDIRECT_URI)
															.build(NaverLoginApi.instance());
		
		OAuthRequest request = new OAuthRequest(Verb.GET, PROFILE_API_URL, oauthService);
		oauthService.signRequest(oauthToken, request);
		Response response = request.send();
		
		return response.getBody();
	}
}
