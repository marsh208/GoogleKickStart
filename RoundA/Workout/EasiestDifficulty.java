import java.util.*;
import java.io.*;

class  EasiestDifficulty {

  public static void main(String args[]) throws IOException
  {

    int numSessions;
    int numAddSessions;
    int numTestCases;
    int maxDifficulty;
    int workoutTimes[];

    ArrayList<Integer> newTimes = new ArrayList<>();
    String line1;
    String line2;


    File file = new File("input.txt");

    BufferedReader br = new BufferedReader(new FileReader(file));

    //number of test cases
    numTestCases = Integer.parseInt(br.readLine());

    while(true)
    {
      //N and K
      line1 = br.readLine();
      if(line1 == null)
      {
        break;
      }
      numSessions = Integer.parseInt(line1.substring(0,line1.indexOf(" ")));
      numAddSessions = Integer.parseInt(line1.substring(line1.indexOf(" ")+1));
      // System.out.println(numSessions);
      // System.out.println(numAddSessions);
      // System.out.println();

      //workout times
      line2 = br.readLine();

      //make int array of house prices
      workoutTimes = setWorkoutTimes(line2);

      // make int array list with times
      ArrayList<Integer> workoutTimesList = new ArrayList<Integer>();
      for(int time : workoutTimes)
      {
        workoutTimesList.add(time);
      }
      // for(int i = 0; i < workoutTimesList.size(); ++i)
      // {
      //   System.out.println(workoutTimesList.get(i));
      // }
      // System.out.println();


      newTimes = fillBigGaps(workoutTimesList, numAddSessions);

      maxDifficulty = findMaxDiff(newTimes);

      System.out.println(maxDifficulty);
    }


  }

  public static int[] setWorkoutTimes(String times)
  {
      String strTimes[] = times.split(" ");
      int workoutTimes[] = new int[strTimes.length];

      for (int i = 0; i < strTimes.length; ++i) {
          String num = strTimes[i];
          workoutTimes[i] = Integer.parseInt(num);
      }
      return workoutTimes;
  }

  //returns new workoutTimes array list where biggest gaps are filled (i.e. 5,15,20 --> 1)
  public static ArrayList<Integer> fillBigGaps(ArrayList<Integer> times, int xtraSessions)
  {
    int biggestGapIndex = 0;
    int biggestGap = 1;
    int thisGap;
    int newLength;
    int time1;
    int time2;

    while(xtraSessions != 0)
    {
      for(int i = 0; i < times.size()-1; ++i)
      {
        time1 = times.get(i);
        time2 = times.get(i+1);

        thisGap = time2 - time1;

        if(thisGap > biggestGap && thisGap > 1)
        {

          biggestGap = thisGap;
          biggestGapIndex = i;
        }
      }

      newLength = biggestGap / 2;
      // System.out.println("New length: " + newLength);
      times.add(biggestGapIndex+1, times.get(biggestGapIndex) + newLength);
      xtraSessions--;
      biggestGap = 1;

    }
    return times;
  }

  public static int findMaxDiff(ArrayList<Integer> times)
  {
    int biggestGap = 1;
    int thisGap;

    for(int i = 0; i < times.size() - 1; ++i)
    {
      thisGap = times.get(i+1) - times.get(i);
      if(thisGap > biggestGap)
      {
        biggestGap = thisGap;
      }
    }
    return biggestGap;
  }

}
