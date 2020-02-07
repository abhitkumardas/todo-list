/*
package com.adtech.todolist.security;

import com.adtech.todolist.codetype.InfoType;
import com.adtech.todolist.service.TransactionInfo;
import com.adtech.todolist.service.UserService;
import com.adtech.todolist.utils.TodoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Collections;

@Component
public class AuthService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestTemplate restTemplate;

    */
/*@Autowired
    private OAuthClientDetailsService oAuthClientDetailsService;*//*


    @Autowired
    private UserService userService;

    @Autowired
    private TodoUtils todoUtils;

    @Autowired
    TransactionInfo transactionInfo;

    */
/*
    public void fetchAccessToken(String clientID, String username, String password) {

        OAuthClientDetails oAuthClientDetails = oAuthClientDetailsService.getByOAuthClientId(clientID);

        if (null != oAuthClientDetails) {
            if (oAuthClientDetails.getClient().getIsActive().equals(RecordStatus.ACTIVE)){
                if (oAuthClientDetails.getClient().getSlug()
                        .equals(kycUtils.toSlug(KycConstant.DEFAULT_CLIENT_NAME))){

                    String clientSecret = clientService.getDecodedClientSecret(oAuthClientDetails.getEncodedClientSecret());

//                String credentials = "javainuse:secret";
                    String credentials = clientID + ":" + clientSecret;
                    String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

                    HttpHeaders headers = new HttpHeaders();
                    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
                    headers.add("Authorization", "Basic " + encodedCredentials);

                    HttpEntity<String> request = new HttpEntity<String>(headers);

                    String serverIpAddress = "127.0.0.1"; // only local communication via loopback ip
                    String serverPort = "8056";

                    String apiUrl = "http://" + serverIpAddress + ":" + serverPort + "/oauth/token"
                            + "?grant_type=password&" + "username=" + username + "&password=" + password;

                    ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

                    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {

                    }else if (responseEntity.getStatusCode().equals(HttpStatus.FORBIDDEN)) {

                    }else {
                        logger.info("Response Status Code : " + responseEntity.getStatusCode());
                    }

                    System.out.println("Response Body : " + responseEntity.getBody());
                }else {
                    transactionInfo.generateException("forbid.login.dashboard", InfoType.ERROR, HttpStatus.FORBIDDEN);
                }
            }else {
                transactionInfo.generateException("clientuser.is.not.active", InfoType.ERROR, HttpStatus.FORBIDDEN);
            }
        }else {
            transactionInfo.generateException("invalid.client.credentials", InfoType.ERROR, HttpStatus.FORBIDDEN);
        }
    }
    *//*

}
*/
