package jp.dcnet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.dcnet.entity.UserAddress;

@Repository
public interface AddressRepository extends JpaRepository<UserAddress, Integer>{

	List<UserAddress> findByUsername(String userName);

	UserAddress findById(int addressId);

	UserAddress deleteById(int addressId);




}