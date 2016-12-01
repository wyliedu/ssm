package com.hszs.stb.common.helper;


public final class UnitTestHelper {

	private static boolean unitTestRunning = false;
	
	public static boolean isUnitTestRunning(){
		return unitTestRunning;
	}
	
	public static void setUnitTestRunning() {
		unitTestRunning = true;
	}
}
