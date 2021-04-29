package jp.dcnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.AccountSafeQuestion;

@Repository
public interface AccountSafeQuestionRepository extends JpaRepository<AccountSafeQuestion, Integer> {

	AccountSafeQuestion findByUserId(int id);


	@Query(value="select *  from account_safety_question where  (user_name =:account) or (user_email=:account)", nativeQuery = true)
	AccountSafeQuestion getQuestion(@Param("account")String account);

}
