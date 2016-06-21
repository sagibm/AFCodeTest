package com.ibm.afcodetest.utils;

import com.ibm.afcodetest.model.AFData;

import java.util.ArrayList;

/**
 * Created by sheldongabriel on 6/16/16.
 */
public interface AFDataListener {

    public void addAFData(AFData data);
    public ArrayList<AFData> getAllAFData();
    public int getAFDataCount();

}
