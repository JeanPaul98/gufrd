package com.acl.mswauth.service.utils;

import java.util.Date;

/**
 * @author kol on 10/28/24
 * @project msw-auth
 */
public interface UtilService {

     String generateUUID();

     String randomCode();

     Date ajoutDate(long day);

     Date ajoutMinute(long min);

     boolean valideExpireDate(Date date);
}
