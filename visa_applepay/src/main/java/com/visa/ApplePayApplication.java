package com.visa;

import com.visa.auth.VISATokenHandler;
import com.visa.config.VISAConfig;
import com.visa.model.VISARequestModel;
import com.visa.response.VISAResponseModel;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ApplePayApplication {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static void main(String[] args) throws Exception {
        // VISARequestModel visaRequestModel = new VISARequestModel(
        // "v-123-b25dd0af-2960-420b-8aaa-49b3e466c00f",
        // "adevicecert",
        // "jD4Aphu+93N2wbBn",
        // "vXWJaBidcTLaJJCF");
        VISARequestModel visaRequestModel = VISARequestModel.DummyData();
        // Set properties of the visaRequestModel as needed

        ApplePayApplication app = new ApplePayApplication();
        VISAResponseModel responseModel = app.appleProvisioningAPIAsync(visaRequestModel);

        // Handle response as needed
    }

    public VISAResponseModel appleProvisioningAPIAsync(VISARequestModel visaRequestModel) throws Exception {
        try {
            String resourcePath = "provisioning/cardData/applePay";
            String queryString = "apikey=" + VISAConfig.API_KEY;
            String requestBody = gson.toJson(visaRequestModel);

            System.out.println("VISAAPIRequestAsync -- ResourcePath : " + resourcePath);
            System.out.println("VISAAPIRequestAsync -- QueryString : " + queryString);
            System.out.println("VISAAPIRequestAsync -- RequestBody : " + requestBody);

            String xPayToken = VISATokenHandler.getXPayToken(resourcePath, queryString, requestBody);

            System.out.println("VISAAPIRequestAsync -- XPayToken : " + xPayToken);

            String visaAPIPath = "/inapp/" + resourcePath + "?" + queryString;

            Request request = new Request.Builder()
                    .url("https://sandbox.api.visa.com" + visaAPIPath)
                    .post(RequestBody.create(requestBody, MediaType.parse("application/json")))
                    .addHeader("X-PAY-TOKEN", xPayToken)
                    .addHeader("Content-Type", "application/json")
                    .build();

            Call call = client.newCall(request);
            Response response = call.execute();

            System.out.println("VISAAPIRequestAsync -- Response : " + response.toString());

            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                VISAResponseModel visaResponseModel = gson.fromJson(responseBody, VISAResponseModel.class);
                System.out.println("Visa Response mode: " + visaResponseModel);
                return visaResponseModel;
            } else {
                String responseBody = response.body().string();
                System.out.println("VISAAPIRequestAsync -- Response unsuccessful");
            }

        } catch (IOException ex) {
            System.out.println("VISAAPIRequestAsync -- Exception : " + ex.toString());
        }

        return null;

    }
}