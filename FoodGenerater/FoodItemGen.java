import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;



class Main {
  static class MenuEntree {
	String entree;
  String recipe;
	MenuEntree(String name, String info)
	{
		entree = name;
    recipe = info;
	}
}
  public static void main(String []args){
     //Creates a vector
    MenuEntree menuOfEntree;
    Vector <MenuEntree> menu = new Vector<>();
    Random rand = new Random(); //seed the random num gen
    try {
      BufferedReader br = new BufferedReader(
      new FileReader("C:\\JavaReadFiles\\FoodItem.txt")); // open file directory path 
      int n = 0; //count of completed entrees increase is in recipet loop
      String userLine = "";
      for (Scanner fileReader = new Scanner(br); fileReader.hasNext();){ 
        String line = fileReader.nextLine();
        if (line.startsWith("<NAME/>")){
          for (int i = 6; i < line.length(); i++){
            if (line.charAt(i) == '<'){
              break;
            }
            userLine += line.charAt(i);
          }
          menuOfEntree = new MenuEntree(userLine, null);
          menu.add(menuOfEntree);

        } else if (line.startsWith("<START/>")){ 
           String temp = "";
            while (fileReader.hasNextLine()){ //I think we need to make a change to a for loop instead
              if (line.endsWith("<END/>")){
                  temp += line;
                  break;
                }
                temp += line;
                temp += "\n";
                line = fileReader.nextLine();
            }
            temp += line;
            userLine = "";
            for (int i = 8; i < temp.length(); i++){
              if (temp.charAt(i) == '<'){
                break;
              }
              userLine += temp.charAt(i);
            }
            menu.elementAt(n).recipe = userLine;
            n++;
        }
        System.out.println(userLine);
        userLine = "";
        if (!fileReader.hasNextLine()){
          fileReader.close();
        }
      }
      System.out.println(menu.elementAt(1).recipe);
      int randNumberCap = menu.size(); 
      System.out.println("\n");//This is the max the Number Gen can go to.
      int int_random = rand.nextInt(randNumberCap);
      System.out.println("- Random Generator");
      System.out.println(menu.elementAt(int_random).entree);
      System.out.println(menu.elementAt(int_random).recipe);

      br.close();
      
    }catch(Exception ex){
      return;
    }

  }
}