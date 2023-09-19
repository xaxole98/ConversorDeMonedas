package com.xaxocode.modules;

import org.json.JSONObject;

public class ConvertorOperations {
    private String json = FileRead.getFileRead();
    private JSONObject jsonObject = new JSONObject(json);
    private String currency;
    private double baseRate;
    private double targetRate;
    private double amount;

    /**
     * We create a method in which this will return a double value, this method will
     * have 3 parameters in which we will place that first you must write a new json
     * with the data for the program that would be the currency: value and all
     * currencies will be based on the dollar, then using the setAmount method (we
     * are going to place the desired amount to convert) the same thing to tell you
     * which CURRENCY_BASE we will convert to CURRENCY_TO_CONVERT using the Setters
     * methods and finally we will carry out an operation in which the amount of
     * money will be multiplied by the RATE which would be the division of the
     * CURRENCY_CONVERT between CURRENCY_BASE in order to have an average
     */
    public double convertor(double amount, String baseRate, String targetRate) {
        FileWrite.setFileWrite();
        setAmount(amount);
        setBaseRate(baseRate);
        setTargetRate(targetRate);
        return getAmount() * (getTargetRate() / getBaseRate());
    }

    /**
     * Using the getAmount method we obtain the amount that the user has placed for
     * the baseRate conversion (Currency that you want to convert to targetRate)
     */
    public double getAmount() {
        return this.amount;
    }

    /** We create a method to set Amount to make conversor */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /** A method to obtain what the currency placed is */
    public String getCurrency() {
        return this.currency;
    }

    /** A method to place what will be the currency placed on this occasion */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /** A method to get currency baseRate */
    public double getBaseRate() {
        return this.baseRate;
    }

    /**
     * A method to set currency BaseRate with jsonObject is an object about
     * JSONOBJECT and key String "rates" to get double rate currency/currency and we
     * use getCurrency to be able to say this will be "The string" that you must
     * bring me its value in the json
     */

    public void setBaseRate(String baseRate) {
        setCurrency(baseRate);
        this.baseRate += jsonObject.getJSONObject("rates").getDouble(getCurrency());
    }

    /** A method to getTargetRate value */
    public double getTargetRate() {
        return this.targetRate;
    }

    /**
     * A method to set currency targetRate with jsonObject is an object about
     * JSONOBJECT and key String "rates" to get double rate currency/currency and we
     * use getCurrency to be able to say this will be "The string" that you must
     * bring me its value in the json
     */

    public void setTargetRate(String targetRate) {
        setCurrency(targetRate);
        this.targetRate += jsonObject.getJSONObject("rates").getDouble(getCurrency());
    }

    /* i think a method getJson is unnecesary */

    // public String getJson() {
    // return this.json;
    // }
}
