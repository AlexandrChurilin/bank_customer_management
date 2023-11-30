package onelab.bank_customer_management.repository;

import onelab.bank_customer_management.entity.BankClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<BankClient, Long> {

}
