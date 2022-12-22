package pl.mswierczek.bank.account.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.mswierczek.bank.account.persistence.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
