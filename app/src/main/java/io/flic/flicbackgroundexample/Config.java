package io.flic.flicbackgroundexample;

import io.flic.lib.FlicManager;

/**
 * Created by Emil on 2015-11-30.
 */
public class Config {
	static void setFlicCredentials() {
		FlicManager.setAppCredentials("[flic-background-example]", "[app-secret]", "Flic Background Example");
	}
}
