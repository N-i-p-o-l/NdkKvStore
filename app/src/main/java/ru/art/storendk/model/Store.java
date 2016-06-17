package ru.art.storendk.model;

/**
 * Created by NArtur on 16.06.2016.
 */
public class Store {
  static {
    System.loadLibrary("store");
  }

  public native int getInteger(String pKey);
  public native void setInteger(String pKey, int pInt);

  public native String getString(String pKey);
  public native void setString(String pKey, String pString);
}
