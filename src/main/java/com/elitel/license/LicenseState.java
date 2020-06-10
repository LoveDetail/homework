package com.elitel.license;

public enum LicenseState {
	NoInit(-1),Ok(0), Working(1), Expired(2), LicError(3), ReadError(4);

	  private int index;

	  private LicenseState(int index) { this.index = index; }

	  public int getIndex() {
	    return this.index;
	  }
}
