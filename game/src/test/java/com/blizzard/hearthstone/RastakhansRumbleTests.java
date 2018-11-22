package com.blizzard.hearthstone;

import net.demilich.metastone.game.actions.PlayChooseOneCardAction;
import net.demilich.metastone.game.cards.Attribute;
import net.demilich.metastone.game.cards.Card;
import net.demilich.metastone.game.cards.CardSet;
import net.demilich.metastone.game.decks.DeckFormat;
import net.demilich.metastone.game.entities.heroes.HeroClass;
import net.demilich.metastone.game.entities.minions.Minion;
import net.demilich.metastone.game.entities.minions.Race;
import net.demilich.metastone.game.targeting.Zones;
import net.demilich.metastone.game.cards.Attribute;
import net.demilich.metastone.tests.util.TestBase;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class RastakhansRumbleTests extends TestBase {

    @Test
    public void testSpiritOfTheShark() {
        runGym((context, player, opponent) -> {
            Minion spirit = playMinionCard(context, player, "minion_spirit_of_the_shark");
            Minion edwin = playMinionCard(context, player, "minion_edwin_vancleef");
            assertEquals(edwin.getAttack(), 6);
            playMinionCardWithBattlecry(context, player, "minion_si7_agent", edwin);
            assertEquals(edwin.getHp(), 2);
            assertTrue(spirit.hasAttribute(Attribute.STEALTH));
            context.endTurn();
            context.endTurn();
            assertTrue(!spirit.hasAttribute(Attribute.STEALTH));
        });

    }

    @Test
    public void testShirvallahTheTiger() {
        runGym((context, player, opponent) -> {
            Card tiger = receiveCard(context, player, "minion_shirvallah_the_tiger");
            assertEquals(costOf(context, player, tiger), 25);
            playCard(context, player, "spell_anyfin_can_happen");
            assertEquals(costOf(context, player, tiger), 15);
            playCard(context, player, "minion_ultrasaur");
            assertEquals(costOf(context, player, tiger), 15);
        });
    }

    @Test
    public void testHexLordMalacrass() {
        runGym((context, player, opponent) -> {
            playCard(context, player, "minion_hex_lord_malacrass");
        });

    }

    @Test
    public void testVoidContract() {
        runGym((context, player, opponent) -> {

            shuffleToDeck(context, player, "minion_wisp");
            for (int i = 0; i < 15; i++) {
                shuffleToDeck(context, player, "minion_wisp");
                shuffleToDeck(context, opponent, "minion_wisp");
            }


            assertEquals(player.getDeck().size(), 16);
            assertEquals(opponent.getDeck().size(), 15);
            playCard(context, player, "spell_void_contract");
            assertEquals(player.getDeck().size(), 8);
            assertEquals(opponent.getDeck().size(), 7);
        });

    }

    @Test
    public void testBaitedArrow() {
        runGym((context, player, opponent) -> {
            playCard(context, opponent, "spell_kara_kazham");
            for (int i = 0; i < 3; i++) {
                playCard(context, player, "spell_baited_arrow", opponent.getMinions().get(0));
            }
            assertEquals(player.getMinions().size(), 2);
        });

    }

    @Test
    public void testPyromaniac() {
        runGym((context, player, opponent) -> {
            shuffleToDeck(context, player, "minion_wisp");
            playCard(context, player, "minion_pyromaniac");
            Minion wisp = playMinionCard(context, opponent, "minion_wisp");
            useHeroPower(context, player, wisp.getReference());
            assertEquals(player.getHand().size(), 1);
        });
    }

    @Test
    public void testTicketScalper() {
        runGym((context, player, opponent) -> {
            shuffleToDeck(context, player, "minion_wisp");
            shuffleToDeck(context, player, "minion_wisp");
            shuffleToDeck(context, player, "minion_wisp");
            shuffleToDeck(context, player, "minion_wisp");
            Minion ticket = playMinionCard(context, player, "minion_ticket_scalper");
            Minion wisp = playMinionCard(context, opponent, "minion_wisp");
            Minion ultrasaur = playMinionCard(context, opponent, "minion_ultrasaur");
            attack(context, player, ticket, wisp);
            assertEquals(player.getHand().size(), 2);
            attack(context, player, ticket, ultrasaur);
            assertEquals(player.getHand().size(), 2);
        });
    }

    @Test
    public void testSpiritOfTheDead() {
        runGym((context, player, opponent) -> {
            playCard(context, player, "minion_spirit_of_the_dead");
            Minion wisp = playMinionCard(context, player, "minion_wisp");
            destroy(context, wisp);
            assertEquals(player.getDeck().size(), 1);
            assertEquals(costOf(context, player, player.getDeck().get(0)), 1);
        });

    }

    @Test
    public void testBwonsamdiTheDead() {
        runGym((context, player, opponent) -> {
            shuffleToDeck(context, player, "minion_wisp");
            shuffleToDeck(context, player, "minion_wisp");
            shuffleToDeck(context, player, "minion_faithful_lumi");
            playCard(context, player, "minion_spirit_of_the_dead");
            for (int i = 0; i < 5; i++) {
                shuffleToDeck(context, player, "minion_faithful_lumi");
                Minion wisp = playMinionCard(context, player, "minion_wisp");
                destroy(context, wisp);
            }

            playCard(context, player, "minion_bwonsamdi_the_dead");
            assertEquals(player.getHand().size(), 10);
            assertEquals(player.getDeck().size(), 3);
        });

    }

    @Test
    public void testZentimo() {
        runGym((context, player, opponent) -> {
            Minion ultra1 = playMinionCard(context, opponent, "minion_ultrasaur");
            Minion ultra2 = playMinionCard(context, opponent, "minion_ultrasaur");
            Minion ultra3 = playMinionCard(context, opponent, "minion_ultrasaur");
            playCard(context, player, "minion_zentimo");
            playCard(context, player, "spell_hex", ultra2);
            for (int i = 0; i < 3; i++) {
                assertEquals(opponent.getMinions().get(i).getSourceCard().getCardId(), "token_frog", i + "");
            }
        });
    }

    @Test
    public void testSpiritOfTheDragonhawk() {
        runGym((context, player, opponent) -> {
            Minion ultra1 = playMinionCard(context, opponent, "minion_ultrasaur");
            Minion ultra2 = playMinionCard(context, opponent, "minion_ultrasaur");
            Minion ultra3 = playMinionCard(context, opponent, "minion_ultrasaur");
            playCard(context, player, "minion_spirit_of_the_dragonhawk");
            useHeroPower(context, player, ultra2.getReference());
            for (int i = 0; i < 3; i++) {
                assertEquals(opponent.getMinions().get(i).getHp(), 13, i + "");
            }

        });
    }

    @Test
    public void testDaringFireEater() {
        runGym((context, player, opponent) -> {
            playCard(context, player, "minion_daring_fire_eater");
            useHeroPower(context, player, opponent.getHero().getReference());
            useHeroPower(context, player, opponent.getHero().getReference());
            assertEquals(opponent.getHero().getHp(), 26);
        });

    }

    @Test
    public void testGralTheShark() {
        runGym((context, player, opponent) -> {
            shuffleToDeck(context, player, "minion_ultrasaur");
            Minion gral = playMinionCard(context, player, "minion_gral_the_shark");
            assertEquals(gral.getAttack(), 9);
            assertEquals(gral.getHp(), 16);
            destroy(context, gral);
            assertEquals(player.getHand().size(), 1);
        });
        runGym((context, player, opponent) -> {
            shuffleToDeck(context, player, "minion_ultrasaur");
            shuffleToDeck(context, player, "minion_ultrasaur");
            shuffleToDeck(context, player, "minion_ultrasaur");
            playMinionCard(context, player, "minion_spirit_of_the_shark");
            Minion gral = playMinionCard(context, player, "minion_gral_the_shark");
            assertEquals(gral.getAttack(), 16);
            assertEquals(gral.getHp(), 30);
            destroy(context, gral);
            assertEquals(player.getHand().size(), 2);
        });

    }

    @Test
    public void testBlastWave() {
        runGym((context, player, opponent) -> {
            playCard(context, player, "spell_stand_against_darkness");
            playCard(context, player, "spell_blast_wave");
            assertEquals(player.getHand().size(), 5);
        });

    }
}