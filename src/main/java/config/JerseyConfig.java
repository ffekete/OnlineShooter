package config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import datahandler.HighScoreTable;

@Component
@ApplicationPath("/jersey")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(HighScoreTable.class);
	}

}