[
    new MagicWhenOtherComesIntoPlayTrigger() {
        @Override
        public MagicEvent executeTrigger(
                final MagicGame game,
                final MagicPermanent permanent,
                final MagicPermanent otherPermanent) {
            return (otherPermanent == permanent || otherPermanent.isArtifact()) ?
                new MagicEvent(
                    permanent,
                    this,
                    "PN puts a charge counter on SN."
                ):
                MagicEvent.NONE;
        }
        
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicChangeCountersAction(event.getPermanent(),MagicCounterType.Charge,1,true));
        }        
    },
    new MagicPermanentActivation(
        [
            MagicConditionFactory.ChargeCountersAtLeast(1),
            MagicCondition.CAN_TAP_CONDITION,
            MagicConditionFactory.ManaCost("{3}")
        ],
        new MagicActivationHints(MagicTiming.Draw),
        "Draw"
    ) {
        @Override
        public MagicEvent[] getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostTapEvent(source,"{3}"),
                new MagicRemoveCounterEvent(
                    source,
                    MagicCounterType.Charge,
                    1
                )
            ];
        }
        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source,final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "PN draws a card"
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new MagicDrawAction(event.getPlayer(),1));
        }
    }
]
