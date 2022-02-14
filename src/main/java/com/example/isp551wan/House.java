package com.example.isp551wan;

import java.util.Date;

public class House extends HouseDetails implements java.io.Serializable{
    public Integer hID;
    public Integer hNoRoom;
    public Integer hNoTenants;

    public House(){

    }

    public House(Integer hID, String hName, Double hMP, String hAddress, Date hPublishDate, Boolean hAvailability, Integer hNoTenants, Integer hNoRoom, Integer hNoToilet, Integer hNoAC, Boolean hWifi, Integer hFurniture, Integer hWM, String desc, String hPicName, Integer hID1, Integer hNoRoom1, Integer hNoTenants1) {
        super(hID, hName, hMP, hAddress, hPublishDate, hAvailability, hNoTenants, hNoRoom, hNoToilet, hNoAC, hWifi, hFurniture, hWM, desc, hPicName);
        this.hID = hID1;
        this.hNoRoom = hNoRoom1;
        this.hNoTenants = hNoTenants1;
    }

    @Override
    public Integer gethID() {
        return hID;
    }

    @Override
    public void sethID(Integer hID) {
        this.hID = hID;
    }

    @Override
    public Integer gethNoRoom() {
        return hNoRoom;
    }

    @Override
    public void sethNoRoom(Integer hNoRoom) {
        this.hNoRoom = hNoRoom;
    }

    @Override
    public Integer gethNoTenants() {
        return hNoTenants;
    }

    @Override
    public void sethNoTenants(Integer hNoTenants) {
        this.hNoTenants = hNoTenants;
    }
}
