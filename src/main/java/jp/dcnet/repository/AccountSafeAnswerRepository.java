package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.AccountSafeAnswer;

@Repository
public interface AccountSafeAnswerRepository extends JpaRepository<AccountSafeAnswer, Integer>{

	AccountSafeAnswer findByUserId(int id);

}
