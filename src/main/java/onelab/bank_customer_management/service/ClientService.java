package onelab.bank_customer_management.service;
import onelab.bank_customer_management.entity.BankClient;
import onelab.bank_customer_management.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<BankClient> getAllClient() {
        LOGGER.info("Getting all clients");
        return repository.findAll();
    }
    public Optional<BankClient> getClientById( Long id) {
        LOGGER.info("Getting client by ID: {}", id);
        return repository.findById(id);
    }

    public BankClient createClient(BankClient client) {
        LOGGER.info("Creating a new client: {}", client);
        return repository.save(client);
    }

    public BankClient updateClient(Long id, BankClient client) {
        LOGGER.info("Updating client with ID {}: {}", id, client);
        if(repository.existsById(id)) {
            client.setId(id);
            return repository.save(client);
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        LOGGER.info("Deleting client with ID: {}", id);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

