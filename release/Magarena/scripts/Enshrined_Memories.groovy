[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                payedCost.getX(),
                this,
                "PN reveals the top RN cards of his or her library. " + 
                "Put all creature cards revealed this way into PN's hand and the rest on the bottom of PN's library."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicCardList topX = event.getPlayer().getLibrary().getCardsFromTop(event.getRefInt()) ;
            game.doAction(new MagicRevealAction(topX));
            for (final MagicCard top : topX) {
                game.doAction(new MagicRemoveCardAction(
                    top,
                    MagicLocationType.OwnersLibrary
                ));
                game.doAction(new MoveCardAction(
                    top,
                    MagicLocationType.OwnersLibrary,
                    top.hasType(MagicType.Creature) ?
                      MagicLocationType.OwnersHand :
                      MagicLocationType.BottomOfOwnersLibrary
                ));
            }
        }
    }
]
