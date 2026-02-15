package org.example.parkinglot;

import lombok.Data;

@Data
public class ParkingSpot {
    int slotNum;
    Vehicle vehicle;
    SpotType spotType;
    boolean isAvailable = true;

    public ParkingSpot(int slotNumber, SpotType slotType) {
        this.slotNum = slotNumber;
        this.spotType = slotType;
    }

    public boolean canVehicleFit(Vehicle vehicle) {
        if (vehicle.type.equals(VehicleType.CAR) && spotType == SpotType.EV) {
            return true;
        }
        if (spotType == SpotType.Handicap) {
            return true;
        }
        return spotType == SpotType.NORMAL;
    }

    public void parkVehicle(Vehicle vehicle) {
        isAvailable = false;
    }

    public void unPark() {
        isAvailable = true;
    }

    public boolean isSpotAvailable() {
        return isAvailable;
    }


}
