package com.myprojects.spring.security.dao.jpa.oauth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myprojects.spring.security.entity.oauth.OauthClientDetails;

public interface OauthClientDetailsJpaDao extends JpaRepository<OauthClientDetails, String> {

	public OauthClientDetails findByClientId(String clientId);

	@Query(value = "select clientId from OauthClientDetails")
	public List<String> findClientIds();

}