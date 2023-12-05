package onelab.bank_customer_management.controller;

import onelab.bank_customer_management.entity.BankClient;
import onelab.bank_customer_management.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<BankClient> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankClient> getClientById(@PathVariable Long id) {
        LOGGER.info("Getting client by ID: {}", id);
        Optional<BankClient> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BankClient> createClient(@RequestBody BankClient client) {
        LOGGER.info("Creating new client: {}", client);
        BankClient savedClient = clientService.createClient(client);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankClient> updateClient(@PathVariable Long id, @RequestBody BankClient client) {
        LOGGER.info("Updating client with ID {}: {}", id, client);
        BankClient updatedClient = clientService.updateClient(id, client);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        LOGGER.info("Deleting client with ID: {}", id);
        return clientService.deleteClient(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
