package game.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import game.config.constant.BrokerPaths;
import game.config.constant.EndpointPaths;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(BrokerPaths.PLAYER_REGISTERED_STATUS,
                BrokerPaths.SEND_SHIPS_DETAILS,
                BrokerPaths.PROVIDE_PLAYER_DATA + 0,
                BrokerPaths.PROVIDE_PLAYER_DATA + 1,
                BrokerPaths.PROVIDE_PLAYER_DATA + 2,
                BrokerPaths.PROVIDE_PLAYER_DATA + 3,
                BrokerPaths.PROVIDE_PLAYER_DATA + 4,
                BrokerPaths.PROVIDE_PLAYER_DATA + 5,
                BrokerPaths.PROVIDE_PLAYER_DATA + 6,
                BrokerPaths.PROVIDE_PLAYER_DATA + 7,
                BrokerPaths.PROVIDE_PLAYER_DATA + 8,
                BrokerPaths.PROVIDE_PLAYER_DATA + 9,
                BrokerPaths.PROVIDE_PLAYER_DATA + 10,
                BrokerPaths.MESSAGE_BROKER,
                BrokerPaths.EVENT_BROKER
                );
        
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(EndpointPaths.REGISTER_PLAYER).withSockJS();
        registry.addEndpoint(EndpointPaths.GET_SHIP_DETAILS).withSockJS();
        registry.addEndpoint(EndpointPaths.UPDATE_PLAYER_DATA).withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"0").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"1").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"2").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"3").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"4").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"5").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"6").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"7").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"8").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"9").withSockJS();
        registry.addEndpoint(EndpointPaths.REQUEST_PLAYER_DATA+"10").withSockJS();
        registry.addEndpoint(EndpointPaths.CREATE_AMMO).withSockJS();
    }

}