package com.unibuc.communityhelpv3.pojos;

import java.util.ArrayList;

/**
 * Created by Tudor on 23.05.2016.
 */
public class LocationsGetBody {

    private ArrayList<Locations> locations;

    public LocationsGetBody(ArrayList<Locations> locations) {
        this.locations = locations;
    }

    public LocationsGetBody() {
        this.locations = new ArrayList<>();
    }

    public ArrayList<Locations> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Locations> locations) {
        this.locations = locations;
    }

    public class Locations {

        int id;
        String name;
        String address;
        Double lat;
        Double lng;

        public Locations() {
        }

        public Locations(int id, String name, String address, Double lat, Double lng) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.lat = lat;
            this.lng = lng;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }
    }
}
