package com.xaxocode.practices.converteroperations;

import com.xaxocode.practices.file.FileRead;
import org.json.JSONObject;
import com.xaxocode.practices.file.FileWrite;

public class ConvertorOperations {
    private String json = FileRead.getFileRead();
    private JSONObject jsonObject = new JSONObject(json);
    private String currency;
    private double baseRate;
    private double targetRate;
    private double amount;

    public double convertor(double amount, String baseRate, String targetRate) {
        FileWrite.setFileWrite();
        setAmount(amount);
        setBaseRate(baseRate);
        setTargetRate(targetRate);
        return getAmount() * (getTargetRate() / getBaseRate());
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBaseRate() {
        return this.baseRate;
    }

    public void setBaseRate(String baseRate) {
        setCurrency(baseRate);
        this.baseRate += jsonObject.getJSONObject("rates").getDouble(getCurrency());
    }

    public double getTargetRate() {
        return this.targetRate;
    }

    public void setTargetRate(String targetRate) {
        setCurrency(targetRate);
        this.targetRate += jsonObject.getJSONObject("rates").getDouble(getCurrency());
    }

    public String getJson() {
        return this.json;
    }
}
