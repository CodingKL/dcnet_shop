package jp.dcnet.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.dcnet.entity.AccountSafeAnswer;
import jp.dcnet.entity.AccountSafeQuestion;
import jp.dcnet.entity.User;
import jp.dcnet.object.SafeQuestionDto;
import jp.dcnet.repository.AccountSafeAnswerRepository;
import jp.dcnet.repository.AccountSafeQuestionRepository;
import jp.dcnet.repository.UserRepository;

@Service
public class AccountSafetyService {
	@Autowired
	AccountSafeQuestionRepository accountQuestion;
	@Autowired
	AccountSafeAnswerRepository accountAnswer;
	@Autowired
	UserRepository userRepository;

	/*
	 * ユーザーがアカウントの安全性問題をアップロード
	 */

	public int saveAccountsafte(SafeQuestionDto safe, int id, String email, String name) {

		AccountSafeQuestion aQuestion = accountQuestion.findByUserId(id);

		AccountSafeAnswer aAnswer = accountAnswer.findByUserId(id);

		if (aQuestion != null && aAnswer != null) {

			return 1;

		} else {
			AccountSafeQuestion aq = new AccountSafeQuestion();
			aq.setUserId(id);
			aq.setUserEmail(email);
			aq.setUserName(name);
			aq.setQuestionOne(safe.getQone());
			aq.setQuestionTow(safe.getQtwo());
			aq.setQuestionThree(safe.getQthree());
			aq.setDataTime(new Timestamp(System.currentTimeMillis()));

			AccountSafeAnswer as = new AccountSafeAnswer();

			as.setUserId(id);
			as.setUserEmail(email);
			as.setUserName(name);
			as.setQuestionOne(safe.getQonean());
			as.setQuestionTow(safe.getQtwoan());
			as.setQuestionThree(safe.getQthreean());
			as.setDataTime(new Timestamp(System.currentTimeMillis()));

			accountQuestion.save(aq);
			accountAnswer.save(as);

			return 0;

		}
	}

	/*
	 * ユーザー　安全問題取る
	 */
	public AccountSafeQuestion getQuestion(String account) {

		AccountSafeQuestion asq = accountQuestion.getQuestion(account);

		if (asq != null) {

			return asq;

		} else {

			return null;
		}

	}

	/*
	 * ユーザー　安全問題の答えを確認
	 */
	public int getUserAnswer(SafeQuestionDto sqDto, int id) {

		AccountSafeAnswer asa = accountAnswer.findByUserId(id);

		if (asa.getQuestionOne().equals(sqDto.getQonean())
				&& asa.getQuestionTow().equals(sqDto.getQtwoan())
				&& asa.getQuestionThree().equals(sqDto.getQthreean())) {

			return 0;

		} else {

			return 1;
		}

	}

	public int changeUserPwd(SafeQuestionDto sqDto, int id) {

		User ur = userRepository.getUserId(id);
		if (ur == null) {

			return 1;
		} else {
			User user = new User();
			user.setId(ur.getId());
			user.setUsername(ur.getUsername());
			user.setPassword(sqDto.getPwd());
			user.setDateTime(new Timestamp(System.currentTimeMillis()));
			user.setRole(ur.getRole());
			user.setStatus(ur.getStatus());
			user.setEmail(ur.getEmail());

			userRepository.save(user);

			return 0;

		}

	}
}
