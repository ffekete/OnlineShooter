package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import config.BrokerPaths;
import config.EndpointPaths;
import datahandler.PlayerPool;
import model.PlayerData;

/** Clients can poll REQUEST_PLAYER_DATA channel to receive server-side information about the given player. */
@Controller
public class DataRequestController {
	
	@Autowired
	private PlayerPool playerPool;
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "0")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "0")
	public PlayerData getUserData_0(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "1")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "1")
	public PlayerData getUserData_1(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "2")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "2")
	public PlayerData getUserData_2(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "3")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "3")
	public PlayerData getUserData_3(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "4")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "4")
	public PlayerData getUserData_4(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "5")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "5")
	public PlayerData getUserData_5(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "6")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "6")
	public PlayerData getUserData_6(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "7")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "7")
	public PlayerData getUserData_7(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "8")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "8")
	public PlayerData getUserData_8(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "9")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "9")
	public PlayerData getUserData_9(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
	
	@MessageMapping(EndpointPaths.REQUEST_PLAYER_DATA + "10")
	@SendTo(BrokerPaths.PROVIDE_PLAYER_DATA + "10")
	public PlayerData getUserData_10(Long id){
		if(id != null){
			PlayerData currentPlayer = playerPool.getPlayerById(id);
			if(currentPlayer != null){
				playerPool.resetInactivityOfPlayer(id);
			}
			return currentPlayer;
		}
		return null;
	}
}
