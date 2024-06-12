package org.sid.sec;

public interface SecurityParams {
    public static final String JWT_HEADER_NAME = "Authorization";
    public static final String SECRET = "budget";
    public static final long EXPIRATION = 10 * 60 * 1000;
    public static final String HEADER_PREFIX = "Bearer ";
}
