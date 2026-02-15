package org.example.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Floor {
    int floorNum;
    List<ParkingSpot> parkingSpotList;

}
