package game.logging;

import game.characters.Character;

public interface BattleObserver {
    void onBattleStart(Character fighter1, Character fighter2);
    void onAttack(Character attacker, Character defender);
    void onCharacterDeath(Character character);
    void onBattleEnd(Character winner, Character loser);
}