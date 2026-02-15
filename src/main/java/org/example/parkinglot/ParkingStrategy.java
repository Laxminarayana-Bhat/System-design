package org.example.parkinglot;

import java.util.List;

public interface ParkingStrategy {
    ParkingSpot slotAllocation(List<Floor> flrs, Vehicle vhcl);
}
