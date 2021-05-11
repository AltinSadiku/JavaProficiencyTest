package com.JavaProficiencyTest.JavaProficiencyTest.Controllers;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.*;
import com.JavaProficiencyTest.JavaProficiencyTest.Services.UserCoinService;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/crypto")
public class CryptoCoinController {

    private UserCoinService userCoinService;

    private final String url = "https://api.coincap.io/v2/assets/";

    @Autowired
    public CryptoCoinController(UserCoinService userCoinService){
        this.userCoinService = userCoinService;
    }

    @RequestMapping(value = "/getAllCoins", method = RequestMethod.GET)
    public List<CryptoCoin> getAllCoins( String bearerToken){

        CryptoCoinList response = getCoinCapResponse(url, CryptoCoinList.class);

        return response.getData();
    }

    @RequestMapping(value = "/markCoin", method = RequestMethod.POST)
    public void markCoin(@RequestBody MarkCoinDto request){
        String urlWithId = url + request.getCoinId();
        CryptoCoinDto response = getCoinCapResponse(urlWithId, CryptoCoinDto.class);
        if(response.getData() == null) {
            throw new BadRequestAlertException("Coin does not exist in CoinCap API");
        }

        userCoinService.markCoin(request.getUserId(), request.getCoinId());
    }

    @RequestMapping(value = "/getFavoriteCoins/{userId}", method = RequestMethod.GET)
    public List<CryptoCoin> getFavoriteCoins(@PathVariable("userId") int userId){
        String coins = userCoinService.getFavoriteCoins(userId);
        String urlWithIds = url + "?ids=" + coins;
        CryptoCoinList response =  getCoinCapResponse(urlWithIds, CryptoCoinList.class);
        return response.getData();
    }

    private <T> T getCoinCapResponse(String url, Class<T> clazz) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> httpHeaders = new HttpEntity<String>("parameters", headers);

        T response = restTemplate.exchange(url, HttpMethod.GET, httpHeaders, clazz).getBody();
        return response;
    }

}
