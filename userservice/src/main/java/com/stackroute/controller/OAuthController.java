package com.stackroute.controller;


import com.stackroute.exception.ExpertNotFoundException;
import com.stackroute.exception.InnovatorNotFoundException;
import com.stackroute.expert.Expert;
import com.stackroute.expert.ExpertService;
import com.stackroute.google.GoogleService;
import com.stackroute.innovator.Innovator;
import com.stackroute.innovator.InnovatorService;
import com.stackroute.utility.CookieUtil;
import com.stackroute.utility.JwtUtil;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Optional;

@RequestMapping({"api/v1/authorize"})
@RestController
public class OAuthController {
    @Value("${innovator_home_page_url}")
    String innovatorHomePageUrl;
    @Value("${expert_home_page_url}")
    String expertHomePageUrl;
    @Autowired
    GoogleService googleService;
    @Autowired
    InnovatorService innovatorService;
    @Autowired
    ExpertService expertService;

    @Value("${Domain}")
    String domain;
    @Value("${innovator_redirect_url}")
    String innovatorRedirectUrl;
    @Value("${expert_redirect_url}")
    String expertRedirectUrl;

    @Value("${logout_redirect_url}")
    String logOutRedirectUrl;

    public OAuthController() {
    }

    @GetMapping({"/innovator/googlelogin"})
    public RedirectView googleInnovatorLogin() {
        RedirectView redirectview = new RedirectView();
        String url = this.googleService.googlelogin(this.innovatorRedirectUrl);
        redirectview.setUrl(url);
        return redirectview;
    }

    @GetMapping({"/expert/googlelogin"})
    public RedirectView googleExpertLogin() {
        RedirectView redirectview = new RedirectView();
        String url = this.googleService.googlelogin(this.expertRedirectUrl);
        redirectview.setUrl(url);
        return redirectview;
    }
    @GetMapping({"/logout"})
    public RedirectView googleLogOut(HttpServletResponse res){
        RedirectView redirectView = new RedirectView();
        CookieUtil.clearCookie(res, "JWT-TOKEN", this.domain);
        redirectView.setUrl(logOutRedirectUrl); //read from config server later
        return redirectView;
    }

    @GetMapping({"/innovator/complete"})
    public RedirectView googleInnovator(@RequestParam("code") String code, HttpServletResponse res) throws ParseException {
        String accessToken = this.googleService.getGoogleAccessToken(code, this.innovatorRedirectUrl);
        System.out.println("accessToken: " + accessToken);
        Innovator user = this.googleService.getGoogleInnovatorProfile(accessToken);
        //save user in repo
        try {
            System.out.println("USER:: " + user.toString());
            this.innovatorService.saveInnovator(user);
        } catch (Exception var8) {
            PrintStream var10000 = System.out;
            LocalDateTime var10001 = LocalDateTime.now();
            var10000.println("In google method " + var10001 + " " + var8.getMessage());
        }
        //get saved user from repo
        Optional<Innovator> repoUser = null;
        try{
            repoUser = innovatorService.getInnovatorByUsername(user.getUsername());
        }
        catch (InnovatorNotFoundException ex){
            ex.getMessage();
        }

        String jwtToken = JwtUtil.addInnovatorToken(res, repoUser);
        CookieUtil.create(res, "JWT-TOKEN", jwtToken, false, -1, this.domain);
        RedirectView redirectview = new RedirectView();
        redirectview.setUrl(this.innovatorHomePageUrl);
        return redirectview;
    }

    @GetMapping({"/expert/complete"})
    public RedirectView googleExpert(@RequestParam("code") String code, HttpServletResponse res) throws ParseException {
        String accessToken = this.googleService.getGoogleAccessToken(code, this.expertRedirectUrl);
        System.out.println("accessToken: " + accessToken);
        Expert user = this.googleService.getGoogleExpertProfile(accessToken);
        //save user in repo
        try {
            System.out.println("USER:: " + user.toString());
            this.expertService.saveExpert(user);
        } catch (Exception var8) {
            PrintStream var10000 = System.out;
            LocalDateTime var10001 = LocalDateTime.now();
            var10000.println("In google method " + var10001 + " " + var8.getMessage());
        }
        //get saved user from repo
        Optional<Expert> repoUser = null;
        try{
            repoUser = expertService.getExpertByUsername(user.getUsername());
        }
        catch (ExpertNotFoundException ex){
            ex.getMessage();
        }

        String jwtToken = JwtUtil.addExpertToken(res, repoUser);
        CookieUtil.create(res, "JWT-TOKEN", jwtToken, false, -1, this.domain);
        RedirectView redirectview = new RedirectView();
        redirectview.setUrl(this.expertHomePageUrl);
        return redirectview;
    }
}
