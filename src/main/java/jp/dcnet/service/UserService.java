package jp.dcnet.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dcnet.entity.User;
import jp.dcnet.object.UserDto;
import jp.dcnet.repository.UserRepository;
import jp.dcnet.repository.UsersRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UsersRepository usersRepository;

	/*
	 * 新規登録処理
	 */
	@Transactional
	public int createUserInfo(UserDto userInfo) {

		int seq = userRepository.getSeq();
		User entity = new User();

		if (usersRepository.findByUsername(userInfo.getName()) == null && usersRepository.findByEmail(userInfo.getEmail()) == null) {
			entity.setId(seq);
			entity.setEmail(userInfo.getEmail());
			entity.setUsername(userInfo.getName());
			entity.setPassword(userInfo.getPassword());
			entity.setDateTime(new Timestamp(System.currentTimeMillis()));
			entity.setRole("user");
			entity.setStatus(0);
			userRepository.save(entity);

			//新規登録成功
			return 0;
		} else {

			//ユーザーの名前を存在している、新規登録できません
			return 1;
		}

	}

	/*
	 * ユーザー登録処理
	 */
	@Transactional
	public int userLogin(UserDto user) {

		//データベースからユーザーの情報収集

		User userName = usersRepository.findByUsername(user.getName());

		User userEmail = usersRepository.findByEmail(user.getEmail());
		//判断
		if (userName == null && userEmail == null) {

			//ユーザー存在しません
			return 1;

		} else if (!userName.getPassword().equals(user.getPassword())
				|| (!userEmail.getPassword().equals(user.getPassword()))) {

			//ユーザーパスワード、エーラ
			return 2;

		} else {
			//登録成功
			return 0;
		}

	}

	/*
	 * ユーザーのパスワードを変更処理
	 */
	@Transactional
	public int updatePwd(UserDto user, String newPwd) {

		User update = usersRepository.findByUsername(user.getName());

		if (!update.getPassword().equals(user.getPassword())) {
			//ユーザーの古いパスワード、不正確
			return 1;
		} else {
			//ユーザーの古いパスワード、正確　　パスワードを更新
			update.setPassword(newPwd);
			update.setDateTime(new Timestamp(System.currentTimeMillis()));
			userRepository.saveAndFlush(update);

			return 0;

		}
	}

	@Transactional
	public int getUserId(String account) {

		return userRepository.findByUserId(account);

	}

	public String getName(String account) {

		return userRepository.findByUsername(account);
	}

	public String getEmail(String account) {

		return userRepository.findByEmail(account);
	}

	@Transactional
	public String getRole(int id) {

		return userRepository.findById(id).get().getRole();
	}

	@Transactional
	public int getStatus(int id) {
		return userRepository.findById(id).get().getStatus();
	}

	@Transactional
	public String getUserName(int id) {

		return userRepository.findById(id).get().getUsername();
	}

	@Transactional
	public String getUserEmail(int id) {

		return userRepository.findById(id).get().getEmail();

	}

	@Transactional
	public List<User> findAll() {

		return userRepository.findAll();
	}

	/*
	 * 管理員修改用戶權限處理
	 */
	@Transactional
	public void usercontorlup(UserDto userDto, int id) {

		User user = userRepository.findById(id).get();
		user.setRole(userDto.getRole());
		user.setDateTime(new Timestamp(System.currentTimeMillis()));

		userRepository.save(user);
	}

	/*
	 * 管理員刪除用戶處理
	 */
	@Transactional
	public void usercontorldelete(UserDto userDto) {

		userRepository.deleteById(userDto.getId());
	}

	/*
	 * 管理員拉黑用戶處理
	 */
	@Transactional
	public void userPullBackList(UserDto userDto) {

		User user = userRepository.findById(userDto.getId()).get();
		user.setStatus(1);

		userRepository.save(user);
	}

	/*
	 *管理員移除用戶黑名單操作
	 */
	@Transactional
	public void userRemoveBackList(UserDto userDto) {

		User user = userRepository.findById(userDto.getId()).get();
		user.setStatus(0);

		userRepository.save(user);
	}
}
