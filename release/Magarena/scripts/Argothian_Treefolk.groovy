[
    new MagicIfDamageWouldBeDealtTrigger(MagicTrigger.PREVENT_DAMAGE) {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicDamage damage) {
            if (damage.getTarget() == permanent && damage.getSource().hasType(MagicType.Artifact)) {
                // Replacement effect. Generates no event or action.
                damage.prevent();
            }
            return MagicEvent.NONE;
        }
    }
]
