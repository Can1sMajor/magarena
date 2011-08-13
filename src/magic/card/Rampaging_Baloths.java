package magic.card;

import magic.data.TokenCardDefinitions;
import magic.model.MagicGame;
import magic.model.MagicPermanent;
import magic.model.MagicPlayer;
import magic.model.action.MagicPlayTokenAction;
import magic.model.event.MagicEvent;
import magic.model.trigger.MagicTrigger;
import magic.model.trigger.MagicTriggerType;

public class Rampaging_Baloths {
    public static final MagicTrigger T = new MagicTrigger(MagicTriggerType.WhenOtherComesIntoPlay) {
		@Override
		public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final Object data) {
			final MagicPlayer player=permanent.getController();
			final MagicPermanent played=(MagicPermanent)data;
			return (player==played.getController()&&played.isLand()) ?
                new MagicEvent(
                        permanent,
                        player,
                        new Object[]{player},
                        this,
                        "Put a 4/4 green Beast creature token onto the battlefield."):
                null;
		}
		@Override
		public void executeEvent(
                final MagicGame game,
                final MagicEvent event,
                final Object data[],
                final Object[] choiceResults) {
			game.doAction(new MagicPlayTokenAction((MagicPlayer)data[0],TokenCardDefinitions.BEAST4_TOKEN_CARD));
		}		
    };
}
