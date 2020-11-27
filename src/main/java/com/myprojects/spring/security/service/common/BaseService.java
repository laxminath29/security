package com.myprojects.spring.security.service.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.myprojects.spring.security.entity.user.User;
import com.myprojects.spring.security.util.LongObfuscator;

public abstract class BaseService {

	public static User getUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		if (authentication.getPrincipal() instanceof User) {
			return ((User) authentication.getPrincipal());
		}
		return null;
	}

	public static Long getUserId() {
		User user = getUser();
		return user == null ? null : user.getUserId();
	}

	public static Long mask(final Long number) {
		return number != null ? LongObfuscator.INSTANCE.obfuscate(number) : null;
	}

	public static Long unmask(final Long number) {
		return number != null ? LongObfuscator.INSTANCE.unobfuscate(number) : null;
	}

}
