package com.xiaoshabao.blog.component;

import org.springframework.stereotype.Service;

import com.xiaoshabao.blog.dto.AccountProfile;
@Service("contextHolderCloudImpl")
public class ContextHolderCloudImpl implements ContextHolder{
	
	@Override
	public AccountProfile getProfile() {
		AccountProfile profile=new AccountProfile(0, null);
		return profile;
	}

	@Override
	public void putProfile(AccountProfile profile) {
//		SecurityUtils.getSubject().getSession(true).setAttribute("profile", profile);		
	}

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

}
