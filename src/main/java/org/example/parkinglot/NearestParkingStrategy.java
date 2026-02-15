package org.example.parkinglot;

import java.util.List;

public class NearestParkingStrategy implements ParkingStrategy {
    @Override
    public ParkingSpot slotAllocation(List<Floor> flrs, Vehicle vhcl) {
        for (Floor f : flrs) {
            for (ParkingSpot ps : f.getParkingSpotList()) {
                if (ps.isAvailable() && ps.canVehicleFit(vhcl)) {
                    return ps;
                }
            }
        }
        return null;
    }
}
