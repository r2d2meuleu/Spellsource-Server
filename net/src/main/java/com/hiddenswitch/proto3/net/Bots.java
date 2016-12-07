package com.hiddenswitch.proto3.net;

import com.hiddenswitch.proto3.net.models.MulliganRequest;
import com.hiddenswitch.proto3.net.models.MulliganResponse;
import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.actions.GameAction;
import net.demilich.metastone.game.behaviour.threat.FeatureVector;
import net.demilich.metastone.game.behaviour.threat.GameStateValueBehaviour;
import net.demilich.metastone.game.logic.GameLogic;

import java.util.stream.Collectors;

/**
 * Created by bberman on 12/7/16.
 */
public class Bots extends Service<Bots> {
	@Override
	public void start() {

	}

	public MulliganResponse mulligan(MulliganRequest request) {
		// Reject cards that cost more than 3
		MulliganResponse response = new MulliganResponse();
		response.discardedCards = request.cards.stream().filter(c -> c.getBaseManaCost() > 3).collect(Collectors.toList());
		return response;
	}

	public RequestActionResponse requestAction(RequestActionRequest request) {
		RequestActionResponse response = new RequestActionResponse();
		GameStateValueBehaviour behaviour = new GameStateValueBehaviour(FeatureVector.getFittest(), "Botty McBotface");
		GameContext context = new GameContext();
		context.setLogic(new GameLogic());
		context.loadState(request.gameState);
		context.setActivePlayerId(request.playerId);
		GameAction action = behaviour.requestAction(context, context.getPlayer(request.playerId), request.validActions);
		response.gameAction = action;
		return response;
	}
}
