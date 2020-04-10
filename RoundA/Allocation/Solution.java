import java.util.*;
import java.io.*;

class  Solution {

  public static void main(String args[]) throws IOException
  {

    int numTestCases;
    int numHouses;
    int budget;
    int cheapest = 0;
    int maxHousesToBuy;
    int houseNumber = 1;

    int housePrices[];
    String line1;
    String line2;

    File file = new File("input.txt");


    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //number of test cases
    numTestCases = Integer.parseInt(br.readLine());

    while(true)
    {
      //N and B
      line1 = br.readLine();
      if(line1 == null)
      {
        break;
      }
      numHouses = Integer.parseInt(line1.substring(0,line1.indexOf(" ")));
      budget = Integer.parseInt(line1.substring(line1.indexOf(" ")+1));
      // System.out.println(numHouses);
      // System.out.println(budget);
      // System.out.println();

      //house prices
      line2 = br.readLine();

      //make int array of house prices
      housePrices = setHousePrices(line2);

      //sort int[] housePrices from cheapest-most expensive
      housePrices = sortAsc(housePrices);

      //find num houses you can buy before going over budget
      maxHousesToBuy = getNumHouses(housePrices, budget);
      System.out.println("Case #" + houseNumber + ": " + maxHousesToBuy);

      houseNumber++;
    }
  }

  public static int[] setHousePrices(String prices)
  {
      String strPrices[] = prices.split(" ");
      int housePrices[] = new int[strPrices.length];

      for (int i = 0; i < strPrices.length; ++i) {
          String num = strPrices[i];
          housePrices[i] = Integer.parseInt(num);
      }
      return housePrices;
  }

  public static int[] sortAsc(int housePrices[])
  {
    int temp;
    for (int i = 1; i < housePrices.length; i++) {
      for (int j = i; j > 0; j--) {
         if (housePrices[j] < housePrices [j - 1]) {
          temp = housePrices[j];
          housePrices[j] = housePrices[j - 1];
          housePrices[j - 1] = temp;
         }
      }
    }
    return housePrices;
  }

  public static int getNumHouses(int housePrices[], int budget)
  {
    int totalSpent = 0;
    int numBoughtHouses = 0;

    for(int price : housePrices)
    {
      if(totalSpent + price > budget)
      {
        break;
      }
      else
      {
        numBoughtHouses++;
        totalSpent+=price;
      }

    }
    return numBoughtHouses;
  }
}
