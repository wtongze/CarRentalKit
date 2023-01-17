package com.wtongze.carrentalkit.model;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Based on Hertz's car type.
 * */
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CarType {
    MANAGER_SPECIAL("A6", "EXAR", "Manager Special"),
    ECONOMY("A", "ECAR", "Chevrolet Spark or similar"),
    COMPACT("B", "CCAR", "Ford Focus or similar"),
    INTERMEDIATE("C", "ICAR", "Mazda 3 or similar"),
    STANDARD("D", "SCAR", "VW Jetta or similar"),
    FULL_SIZE("F", "FCAR", "Chevrolet Malibu or similar"),
    PREMIUM("G", "PCAR", "Nissan Maxima or similar"),
    SMALL_SUV("Q4", "IFAR", "Nissan Rogue or similar"),
    MEDIUM_SUV("L", "SFAR", "Chevrolet Equinox or similar"),
    MEDIUM_CONVERTIBLE("U", "STAR", "Ford Mustang convertible or similar"),
    MEDIUM_ELITE("P4", "RCAR", "Infiniti Q50 or similar"),
    MINIVAN("R", "MVAR", "Chrysler pacifica or similar"),
    LARGE_LUXURY("I", "LCAR", "Chrysler 300 or similar"),
    SMALL_ELITE("S6", "DCAR", "Mercedes CLA250 / Audi A3 or similar"),
    MEDIUM_LUXURY_SUV("T4", "RFAR", "Cadillac XT5 or similar"),
    ELITE_SPORTS("V4", "RSAR", "Mustang GT / Camaro SS or similar"),
    PASSENGER_VAN("M", "FVAR", "Ford Transit 12 passenger or similar"),
    TESLA_MODEL_3_LONG_RANGE("E8", "JCAC", "Tesla Model 3 Long Range"),
    TESLA_MODEL_3_STANDARD_RANGE("E7", "JCAE", "Tesla Model 3 Standard Range"),
    LARGE_ELITE("K", "GCAR", "BMW 530 or similar"),
    INTERMEDIATE_ELECTRIC_ELITE("C4", "JDAE", "Polestar 2 LR Dual Motor or similar"),
    MERCEDES_C63_AMG("H", "DSAR", "Mercedes C63 AMG"),
    MERCEDES_CLA45_AMG("H6", "GSAR", "Mercedes CLA45 AMG"),
    SHELBY_GT_H_FASTBACK("U2", "XSAR", "Shelby GT-H Fastback"),
    POLESTAR_1("E3", "XEAI", "Polestar 1"),
    PREMIUM_SPORTS_CONVERTIBLE("U4", "RTAR", "Chevy Camaro SS Convertible or similar"),
    SHELBY_GT_H_CONVERTIBLE("U3", "XTAR", "Shelby GT-H Convertible"),
    COMPACT_SUV("B4", "CFAR", "Buick Encode or similar"),
    SMALL_LUXURY_SUV("H4", "JFDR", "Infiniti QX50 or similar"),
    TESLA_MODEL_Y("E9", "RFAC", "Tesla Model Y"),
    LARGE_SUV("T", "FFAR", "GMC Yukon or similar"),
    EXTRA_CAPACITY_LARGE_SUV("T6", "PFAR", "Chevrolet Suburban or similar"),
    LARGE_ELITE_SUV("P6", "LFAR", "Infiniti QX80 or similar"),
    LUXURY_PASSENGER_SUV("Z4", "RGDR", "Infiniti QX60 or similar"),
    MEDIUM_PASSENGER_SUV("L4", "FRAR", "Nissan Pathfinder or similar"),
    SMALL_PICKUP_TRUCK("O6", "IPAR", "Nissan Frontier Crew Cab or similar"),
    MEDIUM_PICKUP_TRUCK("S", "SPAR", "Dodge Ram 1500 or similar");

    public final String group;
    public final String code;
    public final String description;
    CarType(String group, String code, String description) {
        this.group = group;
        this.code = code;
        this.description = description;
    }

    public static CarType findByCode(String code) {
        String c = code.toUpperCase();
        for (CarType carType : CarType.values()) {
            if (carType.code.equals(c)) {
                return carType;
            }
        }
        return null;
    }

    public static CarType findByGroup(String group) {
        String g = group.toUpperCase();
        for (CarType carType : CarType.values()) {
            if (carType.group.equals(g)) {
                return carType;
            }
        }
        return null;
    }
}
