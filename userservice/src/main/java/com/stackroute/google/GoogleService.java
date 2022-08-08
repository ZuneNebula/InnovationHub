package com.stackroute.google;

import com.stackroute.expert.Expert;
import com.stackroute.innovator.Innovator;
import org.json.simple.parser.ParseException;

public interface GoogleService {
    String googlelogin(String redirectUrl);

    String getGoogleAccessToken(String code, String redirectUrl);

    Expert getGoogleExpertProfile(String accessToken) throws ParseException;

    Innovator getGoogleInnovatorProfile(String accessToken) throws ParseException;
}

