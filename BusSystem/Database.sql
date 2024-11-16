CREATE DATABASE PublicTransportBatteryManagementSystem

USE PublicTransportBatteryManagementSystem

CREATE TABLE VehicleTypes (
    TypeId INT PRIMARY KEY,
    TypeName VARCHAR(20),
    BatteryCapacity INT
);

CREATE TABLE VehicleTable (
    ID INT PRIMARY KEY,
    TypeId INT,
    Name VARCHAR(20),
    FOREIGN KEY (TypeId) REFERENCES VehicleTypes(TypeId)
);

CREATE TABLE VehicleData (
    VehicleId INT PRIMARY KEY,
    VehicleTypeId INT,
    CurrentBattery INT NULL,
    ChargingFlag BIT,
	RouteID INT,
    FOREIGN KEY (VehicleId) REFERENCES VehicleTable(ID),
    FOREIGN KEY (VehicleTypeId) REFERENCES VehicleTypes(TypeId)
);

CREATE TABLE UserTable(
		
	Username VARCHAR(20)
);

CREATE TABLE UserRelations(
	UserId INT,
	VehicleId INT
	FOREIGN KEY (UserId) REFERENCES UserTable(Id),
	FOREIGN KEY (UserId) REFERENCES UserTable(UserId),
    FOREIGN KEY (VehicleId) REFERENCES VehicleTable(ID)
);

CREATE TABLE BusRoutes(
	ID INT PRIMARY KEY,
	RouteName VARCHAR(20),
	RouteMileage INT
	FOREIGN KEY (ID) REFERENCES VehicleData(RouteID),
);

ALTER TABLE VehicleData
ADD FOREIGN KEY	(RouteID) REFERENCES BusRoutes(ID);

ALTER TABLE UserTable
ADD UserId INT IDENTITY(1,1);

ALTER TABLE UserTable
DROP CONSTRAINT PK_UserTable_Id;

/*
CREATE TABLE UserTable(
	Id int NOT NULL AUTO_INCREMENT,
	Username VARCHAR(20),
	PRIMARY KEY (Id)
);
*/