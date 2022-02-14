package com.example.isp551wan;

import java.util.Date;

public class Room extends HouseDetails implements java.io.Serializable{
    public Integer hID;

    public Room(){

    }

    public Room(Integer hID, String hName, Double hMP, String hAddress, Date hPublishDate, Boolean hAvailability, Integer hNoTenants, Integer hNoRoom, Integer hNoToilet, Integer hNoAC, Boolean hWifi, Integer hFurniture, Integer hWM, String desc, String hPicName, Integer hID1) {
        super(hID, hName, hMP, hAddress, hPublishDate, hAvailability, hNoTenants, hNoRoom, hNoToilet, hNoAC, hWifi, hFurniture, hWM, desc, hPicName);
        this.hID = hID1;
    }

    @Override
    public Integer gethID() {
        return hID;
    }

    @Override
    public void sethID(Integer hID) {
        this.hID = hID;
    }
}
