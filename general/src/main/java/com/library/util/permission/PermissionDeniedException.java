package com.library.util.permission;

/**
 * Created by agile-01 on 6/7/2017.
 * custom permission to throw when user denied for permission
 * useful while developing libraries
 */

public class PermissionDeniedException extends Exception {

    public static final int CAMERA = 1;
    public static final int GALLERY = 2;
    public static final int LOCATION = 3;
    public static final int CONTACTS = 4;
    public static final int STORAGE = 5;
    public static final int CALL = 6;
    private final int mDeniedPermission;

    public PermissionDeniedException(String message, int deniedPermission) {
        super(message);
        this.mDeniedPermission = deniedPermission;
    }

    /**
     * will be one constants defined in this class
     *
     * @return constant representing group of permission for single functional requirement
     */
    public int getDeniedPermission() {
        return mDeniedPermission;
    }
}
