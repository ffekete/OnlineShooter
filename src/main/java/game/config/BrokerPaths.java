package game.config;

/** contains constants used by WebSocketConfig class to add message brokers to the system. */
public class BrokerPaths {
	public static final String PROVIDE_PLAYER_DATA = "/providePlayerData_node";
	public static final String PLAYER_REGISTERED_STATUS = "/playerRegistered";
	public static final String MESSAGE_BROKER = "/messages";
	public static final String EVENT_BROKER = "/events";
}
