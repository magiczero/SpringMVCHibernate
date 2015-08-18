package com.cngc.pm.service;

import com.cngc.pm.model.SysUser;

public interface UserService {

	SysUser getByUsername(String username);
}
