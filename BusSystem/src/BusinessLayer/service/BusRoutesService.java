package BusinessLayer.service;

import DataAccessLayer.models.BusRoutes;
import DataAccessLayer.repositories.BusRoutesRepository;

import java.util.List;

public class BusRoutesService {
    private BusRoutesRepository busRoutesRepository;

    public BusRoutesService() {
        this.busRoutesRepository = new BusRoutesRepository();
    }

    public List<BusRoutes> getAllBusRoutes() {
        return BusRoutesRepository.getAllBusRoutes();
    }

    public BusRoutes getBusRoutesById(int RouteId) {
        return busRoutesRepository.getBusRouteById(RouteId);
    }

    public void updateBusRoutes(BusRoutes busRoutes) {
        busRoutesRepository.updateBusRoutes(busRoutes);
    }

    public void deleteBusRoute(int RouteId) {
        busRoutesRepository.deleteBusRoutes(RouteId);
    }

    public void createBusRoutes(BusRoutes busRoutes) {
        busRoutesRepository.createBusRoutes(busRoutes);
    }
}
