package com.visa.model;

import com.google.gson.Gson;

public class VISARequestModel {
    public String vCardID;
    public String deviceCert;
    public String nonceSignature;
    public String nonce;

    public String getvCardID() {
        return vCardID;
    }

    public void setvCardID(String vCardID) {
        this.vCardID = vCardID;
    }

    public String getDeviceCert() {
        return deviceCert;
    }

    public void setDeviceCert(String deviceCert) {
        this.deviceCert = deviceCert;
    }

    public String getNonceSignature() {
        return nonceSignature;
    }

    public void setNonceSignature(String nonceSignature) {
        this.nonceSignature = nonceSignature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    // String json =
    // "{\"vCardID\":\"v-123-b25dd0af-2960-420b-8aaa-49b3e466c00f\",\"deviceCert\":\"adevicecert\",\"nonceSignature\":\"jD4Aphu+93N2wbBn\",\"nonce\":\"vXWJaBidcTLaJJCF\"}";

    // public VISARequestModel(String vCardID, String deviceCert, String
    // nonceSignature, String nonce) {
    // this.vCardID = vCardID;
    // this.deviceCert = deviceCert;
    // this.nonceSignature = nonceSignature;
    // this.nonce = nonce;
    // }
    /*
     * VISARequestModel myVisaRequestModel = new
     * VISARequestModel("v-123-b25dd0af-2960-420b-8aaa-49b3e466c00f",
     * "adevicecert", "jD4Aphu+93N2wbBn", "vXWJaBidcTLaJJCF");
     */

    public static VISARequestModel DummyData() {
        VISARequestModel visaRequestModel = new VISARequestModel();
        visaRequestModel.vCardID = "v-123-b25dd0af-2960-420b-8aaa-49b3e466c00f";
        visaRequestModel.deviceCert = "adevicecert";
        visaRequestModel.nonceSignature = "jD4Aphu+93N2wbBn";
        visaRequestModel.nonce = "vXWJaBidcTLaJJCF";
        return visaRequestModel;
    }

    /*
     * public static VISARequestModel DummyData() {
     * String json =
     * "{\"vCardID\":\"v-123-b25dd0af-2960-420b-8aaa-49b3e466c00f\",\"deviceCert\":\"adevicecert\",\"nonceSignature\":\"jD4Aphu+93N2wbBn\",\"nonce\":\"vXWJaBidcTLaJJCF\"}";
     * 
     * Gson gson = new Gson();
     * VISARequestModel visaRequestModel = gson.fromJson(json,
     * VISARequestModel.class);
     * return visaRequestModel;
     * }
     */
}
