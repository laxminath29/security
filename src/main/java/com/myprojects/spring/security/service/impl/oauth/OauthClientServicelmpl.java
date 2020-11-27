package com.myprojects.spring.security.service.impl.oauth;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myprojects.spring.security.dao.jpa.oauth.OauthClientDetailsJpaDao;
import com.myprojects.spring.security.entity.oauth.OauthClientDetails;

@Service("oauthClientService")
public class OauthClientServicelmpl {

	@Autowired
	private OauthClientDetailsJpaDao oauthClientDetailsDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() {
		if (oauthClientDetailsDao.findByClientId("myproject-spring-security") == null) {
			OauthClientDetails oauthClientDetails = new OauthClientDetails();
			oauthClientDetails.setAccessTokenValidity(-1);
			oauthClientDetails.setScope("read,write");
			oauthClientDetails.setClientId("myproject-spring-security");
			oauthClientDetails.setAuthorizedGrantTypes("password");
			oauthClientDetails.setAutoapprove("1");
			oauthClientDetails.setClientSecret(passwordEncoder.encode("root"));
			oauthClientDetails.setRefreshTokenValidity(-1);
			oauthClientDetails.setResourceIds("security-api");
			oauthClientDetailsDao.save(oauthClientDetails);
		}
	}

}
