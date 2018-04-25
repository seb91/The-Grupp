package services;

import java.io.*;

public final class SaveGame {

    public static void saveGame(Character character, int lvl, int saveSlot){
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

    public static void saveGame(String test){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("save" + ".txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(test);
        writer.println(123);
        writer.close();
    }

    public static void createGame(int saveSlot){
        // The name of the file to open.
        String fileName = "save" + saveSlot + ".txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }
}
