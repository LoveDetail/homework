package com.elitel.license;

public class LicenseController {

	public LicenseController()
	  {
	    new LicenseThread().start();
	  }

	  public LicenseController(long millis) {
	    new LicenseThread(millis).start();
	  }
}
