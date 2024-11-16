package PresentationLayer.controller;

import BusinessLayer.service.BusRoutesService;
import DataAccessLayer.models.BusRoutes;

import java.util.List;

public class BusRoutesController {
    private BusRoutesService busRoutesService;

    public BusRoutesController() {
        this.busRoutesService = new BusRoutesService();
    }

    public List<BusRoutes> getAllBusRoutes() {
        return busRoutesService.getAllBusRoutes();
    }

    public BusRoutes getBusRoutesById(int RouteId) {
        return busRoutesService.getBusRoutesById(RouteId);
    }

    public void createBusRoute(BusRoutes busRoutes) {
        busRoutesService.createBusRoutes(busRoutes);
    }

    public void updateBusRoute(BusRoutes busRoutes) {
        busRoutesService.updateBusRoutes(busRoutes);
    }

    public void deleteBusRoute(int RouteId) {
        busRoutesService.deleteBusRoute(RouteId);
    }
}
