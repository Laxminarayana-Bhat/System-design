package org.example.parkinglot;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MainParkingLot {

    List<Floor> floors;
    ParkingStrategy parkingStrategy;

    public ParkingSpot park(Vehicle vehicle) {
        ParkingSpot spot = parkingStrategy.slotAllocation(floors, vehicle);

        if (spot != null) {
            spot.parkVehicle(vehicle);
            System.out.println("Parked");
        } else {
            System.out.println("Cant park");
        }
        return spot;
    }

    public void unPark(ParkingSpot parkingSpot) {
        parkingSpot.unPark();
        System.out.println("Un parked");
    }

}
