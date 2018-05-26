package services;

import java.io.*;
import java.util.Scanner;

public final class SaveGame {

    public static void saveGame(int lvl, int saveSlot) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("./assets/saves/save" + saveSlot + ".txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //writer.println(character.toString());
        if (lvl < createGame(saveSlot)){
            lvl = createGame(saveSlot);
        }
        writer.println("level");
        writer.println(lvl);
        writer.close();
    }

    public static int createGame(int saveSlot){
        int lvl = 1;
        try (Scanner sc = new Scanner(new File("./assets/saves/save" + saveSlot + ".txt"))) {
            while (sc.hasNext()) {
                if (sc.next().equals("level")) {
                    lvl = Integer.parseInt(sc.next());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lvl;
    }
}
