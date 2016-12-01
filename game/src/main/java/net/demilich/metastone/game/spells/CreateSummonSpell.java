package net.demilich.metastone.game.spells;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.demilich.metastone.game.GameContext;
import net.demilich.metastone.game.Player;
import net.demilich.metastone.game.cards.CardSet;
import net.demilich.metastone.game.cards.CardType;
import net.demilich.metastone.game.cards.MinionCard;
import net.demilich.metastone.game.cards.Rarity;
import net.demilich.metastone.game.cards.desc.MinionCardDesc;
import net.demilich.metastone.game.entities.Entity;
import net.demilich.metastone.game.entities.heroes.HeroClass;
import net.demilich.metastone.game.spells.desc.SpellArg;
import net.demilich.metastone.game.spells.desc.SpellDesc;

public class CreateSummonSpell extends Spell {
	
	Logger logger = LoggerFactory.getLogger(CreateSummonSpell.class);

	@Override
	protected void onCast(GameContext context, Player player, SpellDesc desc, Entity source, Entity target) {
		String description = "";
		MinionCardDesc minionCardDesc = new MinionCardDesc();
		minionCardDesc.id = context.getLogic().generateCardID();
		minionCardDesc.name = desc.getString(SpellArg.NAME);
		minionCardDesc.baseAttack = desc.getValue(SpellArg.ATTACK_BONUS, context, player, target, source, 0);
		minionCardDesc.baseHp = desc.getValue(SpellArg.HP_BONUS, context, player, target, source, 0);
		minionCardDesc.heroClass = HeroClass.ANY;
		minionCardDesc.type = CardType.MINION;
		minionCardDesc.rarity = Rarity.FREE;
		minionCardDesc.description = description;
		//minionCardDesc.attributes.put(key, value);
		minionCardDesc.set = CardSet.BASIC;
		minionCardDesc.collectible = false;
		minionCardDesc.baseManaCost = desc.getValue(SpellArg.MANA, context, player, target, source, 0);
		MinionCard newCard = (MinionCard) minionCardDesc.createInstance();
		context.addTempCard(newCard);
		
		int boardPosition = SpellUtils.getBoardPosition(context, player, desc, source);
		int count = desc.getValue(SpellArg.VALUE, context, player, target, source, 1);
		SpellDesc spell = (SpellDesc) desc.get(SpellArg.SPELL);
		for (int i = 0; i < count; i++) {
			MinionCard minionCard = (MinionCard) newCard.clone();
			if (context.getLogic().summon(player.getId(), minionCard.summon(), null, boardPosition, false) && spell != null) {
				SpellUtils.castChildSpell(context, player, spell, source, target);
			}
		}
	}
}