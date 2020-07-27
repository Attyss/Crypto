import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CryptoAndEncrypto {
    public static void main (String[] args){
        Scanner Input = new Scanner (System.in);
        System.out.println("Hi, please type below the text that will be normalized");
        String OriginalText = Input.nextLine();
        System.out.println(" This is some really great Text,  look it again!\n" +
                        "\"" + OriginalText + "\"");
        System.out.println("please type now below the encrypt key");
        int Key = Input.nextInt();
        System.out.println("well, tell us how many groups you what your code");
        int Groups = Input.nextInt();

        encryptString(OriginalText, Key, Groups);
    }
    //here will be the text normalized
    public static String normalizeText (String OriginalText){
        String Text01 = OriginalText.replaceAll("\\s", ""); //Remove all spaces through an replacement Method
        String Text02 = Text01.replaceAll("\\p{Punct}", "");//Remove any punctuation
        String NormalizedText = Text02.toUpperCase(Locale.ROOT);                       //Convert to upper-case letters
        return NormalizedText; //this text will be encrypted in other mothod
    }
    //Insert OB in front of every vowel
    public static String obify (String NormalizedText){
        Pattern Vowels = Pattern.compile("[AEIYOU]"); //used to find the vowel
        int Total = NormalizedText.length();
        int n = 0;
        String ObifiedText = "";
        String OneLetter = "";
        while ((n + 1) <= Total) {
            OneLetter = NormalizedText.substring(n, (n + 1)); //it take letter to letter
            Matcher AEIOU = Vowels.matcher(OneLetter); // and analise if that is a vowel across Pattern-Matcher Methods
            if (AEIOU.find()){
                OneLetter = OneLetter + "OB";
            }
            ObifiedText = ObifiedText + OneLetter;
            n++;
        }
        return ObifiedText;
    }
    //Encryption Algorithm of Caesar
    public static String ceasarify(String NormalizedText, int Key) {
        int ShiftKey;
        String EncryptedText = "";
        if (Key == -26 || Key >= 0){ //it is necessary to avoid of errors
            ShiftKey = (Key % 26);
        }else{
            ShiftKey = ((Key % 26) + 26);
        }
        int TextLength = NormalizedText.length(); //0,1,2,3,..n. n is finite!
        int i = 0;
        for(; i < (TextLength); ++i) {
            char OneLetter = NormalizedText.charAt(i); //it pick up the number of the char in windows
            char OneLetter_Encrypted = (char) (OneLetter + ShiftKey); //and move the char to other(Shiftkey) location
            if (OneLetter_Encrypted > 90){                              //after one cycle the sequence of Encryption, it will back to top
                int Border =  OneLetter_Encrypted - 90;
                OneLetter_Encrypted = (char) ('A' + Border -1);         //und begin again the assignation
            }
            EncryptedText = EncryptedText + OneLetter_Encrypted;
        }
        return EncryptedText; // ceasarifiedText
    }
    //break in Groups and assigment of a X to end of the Group
    public static String groupify (String EncryptedText, int groups) {
        String GroupifiedText = "";
        String LastGroup = "";
        int Total = EncryptedText.length();
        if (Total < groups) {
            String Joker = "";
            while (Total < groups) {
                Joker = Joker + "x";
                Total++;
            }
            GroupifiedText = EncryptedText + Joker;
        } else if (Total == groups) {
            GroupifiedText = EncryptedText;
        } else {
            int n = 0;
            int i = groups;
            while (((n + 1) * i) < Total) {
                GroupifiedText = GroupifiedText + EncryptedText.substring((n * i), (n + 1) * i) + " ";
                n++;
            }
            LastGroup = EncryptedText.substring((n * i), Total);
            int Interval_Length = LastGroup.length(); //size of last group
            String Joker = "";
            while (Interval_Length < i) {
                Joker = Joker + "x";
                Interval_Length++;
            }
            LastGroup = LastGroup + Joker;
        }
        GroupifiedText = GroupifiedText + LastGroup;
        return GroupifiedText;
    }
    //Put all in One String
    public static String encryptString (String OriginalText, int Key, int Groups){
        String Text = normalizeText(OriginalText);
        String Ob = obify(Text);
        String TextCod = ceasarify(Ob, Key);
        String Gro = groupify(TextCod, Groups);
        System.out.println(Gro);
        return Gro;
    }
}

