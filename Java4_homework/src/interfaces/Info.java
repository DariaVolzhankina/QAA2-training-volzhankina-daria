package interfaces;

@FunctionalInterface
public interface Info {
    
    void getInfoCharacter();

    default void getInfoGame(){
        System.out.println("It's MMORPG");
    }
}
