package com.ylym.sunscreen.protectsunscreen;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.ylym.sunscreen.protectsunscreen.UvResponse.UvLevel;

@RestController
public class UvIndexController {

  @Autowired
  private OpenUvApiHandler openUvApiHandler;

  @GetMapping("/uv")
  public UvResponse getUvIndex(@RequestParam() Double lat, @RequestParam Double lon) throws Exception {
    JSONObject responseObject = openUvApiHandler.RequestBasedOnLocation(lat, lon);
    JSONObject resultObject = responseObject.getJSONObject("result");
    Double uvIndex = resultObject.getDouble("uv");
    UvResponse uvResponse = new UvResponse(uvIndex, parseUvLevelBasedOnIndex(uvIndex));
    return uvResponse;
  }

  private UvLevel parseUvLevelBasedOnIndex(Double index) {
    if (index < 3) {
      return UvLevel.LOW;
    }
    if (index < 6) {
      return UvLevel.MOD;
    }
    if (index < 8) {
      return UvLevel.HIGH;
    }
    if (index < 11) {
      return UvLevel.VHIGH;
    }
    return UvLevel.EXTR;
  }
}
