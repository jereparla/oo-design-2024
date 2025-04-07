package game.logging;

public interface BattleSubject {
    void registerObserver(BattleObserver observer);
    void removeObserver(BattleObserver observer);
}