package services;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SaveGame {

    private void saveGame(Character character, int lvl, int saveSlot){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("save" + saveSlot + ".txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(character.toString());
        writer.println(lvl);
        writer.close();
    }
}
