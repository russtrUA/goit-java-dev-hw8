package ua.goit;

import ua.goit.service.ClientService;
import ua.goit.service.entity.Client;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        long goITCompanyId = clientService.create("GoIT company");
        System.out.println("goITCompanyId = " + goITCompanyId);
        System.out.println("clientService.getById(goITCompanyId) = " + clientService.getById(goITCompanyId));
        clientService.setName(goITCompanyId, "GoIT Company");
        System.out.println("clientService.getById(goITCompanyId) after update = " + clientService.getById(goITCompanyId));
        clientService.deleteById(goITCompanyId);
        List<Client> clients = clientService.listAll();
        System.out.println("clients = " + clients);
    }
}
