package com.example.lor_deck_maker.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.lor_deck_maker.data.LoRCard;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoRUtils {
 /*
        public static byte[] Decode(string encoded)
        {
            // Remove whitespace and separators
            encoded = encoded.Trim().Replace(SEPARATOR, "");

            // Remove padding. Note: the padding is used as hint to determine how many
            // bits to decode from the last incomplete chunk (which is commented out
            // below, so this may have been wrong to start with).
            encoded = Regex.Replace(encoded, "[=]*$", "");

            // Canonicalize to all upper case
            encoded = encoded.ToUpper();
            if (encoded.Length == 0)
            {
                return new byte[0];
            }
            int encodedLength = encoded.Length;
            int outLength = encodedLength * SHIFT / 8;
            byte[] result = new byte[outLength];
            int buffer = 0;
            int next = 0;
            int bitsLeft = 0;
            foreach (char c in encoded.ToCharArray())
            {
                if (!CHAR_MAP.ContainsKey(c))
                {
                    throw new DecodingException("Illegal character: " + c);
                }
                buffer <<= SHIFT;
                buffer |= CHAR_MAP[c] & MASK;
                bitsLeft += SHIFT;
                if (bitsLeft >= 8)
                {
                    result[next++] = (byte)(buffer >> (bitsLeft - 8));
                    bitsLeft -= 8;
                }
            }
            // We'll ignore leftover bits for now.
            //
            // if (next != outLength || bitsLeft >= SHIFT) {
            //  throw new DecodingException("Bits left: " + bitsLeft);
            // }
            return result;
        }

     public static String encode(LoRDeck deck)
    {
        List<Integer> result = new ArrayList<>();

        if (deck.getDeck().containsKey(null))
        {
            return null;
        }

        // format and version = 0001 0011
        result.add(0b0001_0011);

        Map<Integer, List<Map.Entry<LoRCard, Integer>>> counts = deck.getDeck().entrySet().stream()
                                                                     .collect(Collectors.groupingBy(Entry::getValue));
        List<List<Map.Entry<LoRCard, Integer>>> grouped3 = groupByFactionAndSetSorted(counts.getOrDefault(3, new ArrayList<>()));
        List<List<Map.Entry<LoRCard, Integer>>> grouped2 = groupByFactionAndSetSorted(counts.getOrDefault(2, new ArrayList<>()));
        List<List<Map.Entry<LoRCard, Integer>>> grouped1 = groupByFactionAndSetSorted(counts.getOrDefault(1, new ArrayList<>()));
        List<Map.Entry<LoRCard, Integer>> nOfs = counts.entrySet().stream()
                                                       .filter(e -> e.getKey() > 3)
                                                       .flatMap(e -> e.getValue().stream())
                                                       .collect(Collectors.toList());

        result.addAll(encodeGroup(grouped3));
        result.addAll(encodeGroup(grouped2));
        result.addAll(encodeGroup(grouped1));
        result.addAll(encodeNofs(nOfs));

        return Base32.encodeBoxed(result);
    }
  *//*
 public static String decode(String code)
 {
     //LoRDeck    deck  = new LoRDeck();
     List<Byte> bytes = new ArrayList<>(Arrays.asList(Base32.decodeBoxed(code)));

     int firstByte = bytes.remove(0);
     int format    = firstByte >>> 4;
     int version   = firstByte & 0xF;

     int MAX_KNOWN_VERSION = 3;
     if (version > MAX_KNOWN_VERSION)
     {
         throw new IllegalArgumentException("The provided code requires a higher version of this library; please update");
     }

     for (int i = 3; i > 0; i--)
     {
         int groupCount = VarInt.pop(bytes);

         for (int j = 0; j < groupCount; j++)
         {
             int itemCount = VarInt.pop(bytes);
             int set       = VarInt.pop(bytes);
             int faction   = VarInt.pop(bytes);

             for (int k = 0; k < itemCount; k++)
             {
                 int    card          = VarInt.pop(bytes);
                 String setString     = Utils.padLeft(String.valueOf(set), "0", 2);
                 String factionString = LoRFaction.fromID(faction).getShortCode();
                 String cardString    = Utils.padLeft(String.valueOf(card), "0", 3);

                 deck.addCard(LoRCard.create(setString, factionString, cardString), i);
             }
         }
     }

     while (bytes.size() > 0)
     {
         int count   = VarInt.pop(bytes);
         int set     = VarInt.pop(bytes);
         int faction = VarInt.pop(bytes);
         int number  = VarInt.pop(bytes);

         String setString     = Utils.padLeft(String.valueOf(set), "0", 2);
         String factionString = LoRFaction.fromID(faction).getShortCode();
         String numberString  = Utils.padLeft(String.valueOf(number), "0", 3);

         deck.addCard(LoRCard.create(setString, factionString, numberString), count);
     }

     return deck;
 }
*/
    public static final String TAG = LoRUtils.class.getSimpleName();
    public static void makeFile(String deckName, List<String> cardCodes, Context ctx) {
        //File fileName = new File(deckName + ".txt");
        File dir = new File(ctx.getFilesDir(), "mydir");
        if (!dir.exists()) {
            dir.mkdir();
        }
            try {
                String fileName = deckName + ".txt";
                File file = new File(dir, fileName);
                FileWriter write = new FileWriter(file);
                FileOutputStream outputStream;
                Log.d(TAG,"file path" + file.toString());
                for (String code : cardCodes) {
                    try {
                        write.write(code + System.lineSeparator());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(TAG, "oops we fucked up");
                    }
                }
                try {
                    write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(TAG, "oops we fucked up");
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "oops we couldn't open");
                return;
            }
        }




        public static List<String> readFile(File toRead){

            List<String> cardCodeList = new ArrayList<String>();

            try(Scanner s = new Scanner(toRead)){
                cardCodeList.add(s.nextLine());
            }catch (FileNotFoundException e){
                e.printStackTrace();
                Log.d(TAG,"oops we fucked up");
            }
            return cardCodeList;
        }

}
