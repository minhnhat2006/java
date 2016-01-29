package com.sharpinu.web.dwr;

public interface IDwrUserService {
	boolean isEmailExisting(String email);

	boolean sendEmailForResetPassword(String email);
}
