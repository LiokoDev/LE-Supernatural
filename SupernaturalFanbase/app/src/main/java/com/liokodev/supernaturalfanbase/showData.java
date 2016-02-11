package com.liokodev.supernaturalfanbase;

/**
 * Created by Jake on 2/11/16.
 */

// This is used by the recyclerView. It will take the information
// from the Activity and send it to the Adapter

class showData {
    String sName, sUrl,  sPhoto, sShortDesc;

    showData(String sName, String sUrl, String sPhoto, String sShortDesc) {
        this.sName = sName;
        this.sUrl = sUrl;
        this.sPhoto = sPhoto;
        this.sShortDesc = sShortDesc;
    }
}
