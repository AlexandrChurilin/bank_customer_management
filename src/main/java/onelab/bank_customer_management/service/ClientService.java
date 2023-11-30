package onelab.bank_customer_management.service;
import onelab.bank_customer_management.entity.BankClient;
import onelab.bank_customer_management.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@Service
public class ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<BankClient> getAllClient() {
        return repository.findAll();
    }
    public Optional<BankClient> getClientById( Long id) {
        return repository.findById(id);
    }

    public BankClient createClient(BankClient client) {
        return repository.save(client);
    }

    public BankClient updateClient(Long id, BankClient client) {
        if(repository.existsById(id)) {
            client.setId(id);
            return repository.save(client);
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}

