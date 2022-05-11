package main;
public enum ConstantsForScenes {
    
    MAIN_MENU, EDIT, SETTINGS, PLAY, FINISH;
    public static ConstantsForScenes constantsForScenes = MAIN_MENU;
    public static void set(ConstantsForScenes constant){
        constantsForScenes = constant;
    }
}
