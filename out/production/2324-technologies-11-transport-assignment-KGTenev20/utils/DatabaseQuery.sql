CREATE DATABASE TestDatabase

USE TestDatabase

CREATE TABLE VehicleTypes (
    TypeId INT PRIMARY KEY IDENTITY(1,1),
    TypeName VARCHAR(20),
    BatteryCapacity INT
);

CREATE TABLE VehicleTable (
    Id INT PRIMARY KEY IDENTITY(1,1),
    TypeId INT,
    Name VARCHAR(20) UNIQUE,
    FOREIGN KEY (TypeId) REFERENCES VehicleTypes(TypeId)
);

CREATE TABLE UserTable (
    UserId INT PRIMARY KEY IDENTITY(1,1),
    Username VARCHAR(20)
);

CREATE TABLE UserRelations (
    UserId INT,
    VehicleId INT,
    FOREIGN KEY (UserId) REFERENCES UserTable(UserId),
    FOREIGN KEY (VehicleId) REFERENCES VehicleTable(Id)
);

CREATE TABLE BusRoutes (
    RouteId INT PRIMARY KEY IDENTITY(1,1),
    RouteMileage INT
);

CREATE TABLE VehicleData (
    VehicleId INT PRIMARY KEY,
    VehicleTypeId INT,
    CurrentBattery INT,
    ChargingFlag BIT,
    RouteId INT,
    FOREIGN KEY (VehicleId) REFERENCES VehicleTable(Id),
    FOREIGN KEY (VehicleTypeId) REFERENCES VehicleTypes(TypeId),
    FOREIGN KEY (RouteId) REFERENCES BusRoutes(RouteId)
);
