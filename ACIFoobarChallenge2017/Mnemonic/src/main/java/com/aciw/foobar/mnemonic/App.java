package com.aciw.foobar.mnemonic;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
 
import com.aciw.foobar.utility.RESTClient;
 
/**
 *
 * @author Administrator
 *
 */
public class App {
    private static final String username = "Team Break";
    private static final String password = "E7WBVI3";
 
    // Inputs for set1 (Ent1) and the bonus set (Ent2)
    private static final String Ent1 = "7f7f7f7f7f7f7f7f";
    private static final String Ent2 = "7f7f7f7f7f7f7f7e";
    
 
   
    public static ArrayList<String> wordList = new ArrayList<String>();
       
    public static File wordListInput = new File("./files/1/Word_List_Input_Set_1.txt");
   
    // This skeleton is convert a single entropy to a mnemonic sentence.
    // You must modify the code to convert 2 entropys to mnemonic sentences.
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
 
        RESTClient.setUsername(username);
        RESTClient.setPassword(password);
 
        // Insert Code here...
        	
        loadFiles();
 
        byte[] ent1Array = Ent1.getBytes("UTF-8");
        byte[] ent2Array = Ent2.getBytes("UTF-8");
        byte[] hashEnt1Array = hashingEnt(Ent1);
        byte[] hashEnt2Array = hashingEnt(Ent2);
        boolean[] bitEnt1Array = byteArray2BitArray(ent1Array);
        boolean[] bitHashEnt1Array = byteArray2BitArray(hashEnt1Array);
        boolean[] bitEnt2Array = byteArray2BitArray(ent2Array);
        boolean[] bitHashEnt2Array = byteArray2BitArray(hashEnt2Array);
       
        int checkSum1 = bitEnt1Array.length/32;
        int checkSum2 = bitEnt2Array.length/32;
       
        String bCheckSum1 = Integer.toBinaryString(checkSum1);
        String bCheckSum2 = Integer.toBinaryString(checkSum2);
       
        String finalCheckSum1 = convertArrayToString(bitEnt1Array) + "0100";
        String finalCheckSum2 = convertArrayToString(bitEnt2Array) + "0100";
        //Methods begin here
        
        System.out.println("length1: " +finalCheckSum1.length());
        System.out.println("length2: " +finalCheckSum2.length());
       
        int bitSize1 = (finalCheckSum1.length())/12;
       
        int bitSize2 = (finalCheckSum2.length())/22;
        
        System.out.println("bitsize1: " +bitSize1);
        System.out.println("bitsize2: " +bitSize2);
        
        ArrayList<Integer> indeces1 = new ArrayList<Integer>();
        ArrayList<Integer> indeces2 = new ArrayList<Integer>();
        
        ArrayList<String> output1 = new ArrayList<String>();
        ArrayList<String> output2 = new ArrayList<String>();
       
       for(int i = 0, j = bitSize2; i < finalCheckSum2.length()-1;)
       {
    	   indeces2.add(convertThing(finalCheckSum2.substring(i, j)));
    	   i = j;
    	   j += bitSize2;
       }
       
       for(int i = 0, j = bitSize1; i < finalCheckSum1.length()-1;)
       {
    	   
    	   indeces1.add(convertThing(finalCheckSum1.substring(i, j)));
    	   i = j;
    	   j += bitSize1;
       }
       
       System.out.println(indeces1);
       System.out.println(indeces2);
       
       for(Integer index : indeces1)
    	   output1.add(wordList.get(index));
       for(Integer index : indeces2)
    	   output2.add(wordList.get(index));
       
       
       
        // The final step is to take these concatenated bits and split
        // them into groups of bits. Each group encodes number
        // which is a position in a wordlist. We convert numbers into
        // words and use joined words as mnemonic sentence.
        // ArrayList<String> OutputWords = getWords(concatBits);
 
        // Submit answer
      output1.remove(output1.size()-1);
       for(String s : wordList)
       {
    	   output1.add(s);
           RESTClient.sendOutput(output1,1);
    	   output1.remove(output1.size()-1);
       }
    	   

       RESTClient.sendOutput(output2,2);
    }
    
    public static int convertThing(String s)
    {
    	int sum = 0;
    	
    		sum = Integer.parseInt(s, 2);
    		System.out.println(sum);
    	
    	return sum;
    }
   
    public static String convertArrayToString(boolean[] arr)
    {
        String result = "";
        for(Boolean b: arr)
        {
            if(b == true)
                result += 1;
            else
                result += 0;
        }
        
        return result;
    }
   
    public static boolean[] byteArray2BitArray(byte[] bytes)
    {
        boolean[] bits = new boolean[bytes.length * 8];
        for (int i = 0; i < bytes.length * 8; i++)
        {
          if ((bytes[i / 8] & (1 << (7 - (i % 8)))) > 0)
            	bits[i] = true;
          else
        	  bits[i] = false;
        	
        }
        return bits;
    }
 
    public static byte[] hashingEnt(String in) throws NoSuchAlgorithmException
    {
 
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(in.getBytes());
        byte byteData[] = md.digest();
        return byteData;
    }
   
   
   
    public static void loadFiles() throws IOException
    {
       
        Scanner in;
        String line;
       
        in = new Scanner(wordListInput);
        while(in.hasNext())
        {
            line =  in.nextLine();
            if(!(line.isEmpty() || line.equals("")))
                wordList.add(line);
        }
        in.close();
    }
   
    public static ArrayList<String> getWords(boolean[] concatBits) {
        /*
         * Guide to get a word from bits, will have to be put in for loop to go
         * through whole boolean array:
         *
         * for (int j = 0; j < groupOfBits; ++j)
         *{
         *  index <<= 1;
         *  if(concatBits[(i * groupOfBits) + j])
         *      index |= 0x1
         *}
         * words.add(wordList.get(index));
         *
         */
 
        // replace null with result object
        return null;
    }
}