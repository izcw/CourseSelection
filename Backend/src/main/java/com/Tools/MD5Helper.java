package com.Tools;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Helper {
    public static String encryptToMD5(String str) {
        return DigestUtils.md5Hex(str);
    }
}
