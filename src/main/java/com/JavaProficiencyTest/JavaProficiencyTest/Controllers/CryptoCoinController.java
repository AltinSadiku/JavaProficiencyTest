package com.JavaProficiencyTest.JavaProficiencyTest.Controllers;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.CryptoCoin;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.CryptoCoinList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CryptoCoinController {



    @RequestMapping(value = "/getAllCoins", method = RequestMethod.GET)
    public List<CryptoCoin> getAllCoins(@RequestHeader("Authorization") String bearerToken){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> httpHeaders = new HttpEntity<String>("parameters", headers);
        CryptoCoinList response = restTemplate.exchange("https://api.coincap.io/v2/assets", HttpMethod.GET,httpHeaders,CryptoCoinList.class).getBody();

        return response.getData();
    }







}
