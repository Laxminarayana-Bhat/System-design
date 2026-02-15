package org.example.parkinglot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    int number;
    VehicleType type;
}
