package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/** Websocket related configuration class.
 *  Contains:
 *  - Message broker configuration to send messages
 *  - Message endpoint configuration to receive messages
 */
@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/** configuring all message brokers to send messages to clients. */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(BrokerPaths.PLAYER_REGISTERED_STATUS, BrokerPaths.PROVIDE_PLAYER_DATA);
		
		config.setApplicationDestinationPrefixes("/app");
	}

	/** configuring all message endpoints to receive messages from client. */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(EndpointPaths.REGISTER_PLAYER).withSockJS();
		registry.addEndpoint(EndpointPaths.UPDATE_PLAYER_DATA).withSockJS();
		registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA).withSockJS();
	}

}