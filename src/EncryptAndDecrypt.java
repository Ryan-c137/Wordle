public class EncryptAndDecrypt {
    public static String encrypt(String plainText) {
        char[] plainChar = plainText.toLowerCase().toCharArray();
        String encryptedText = new String();
        for (char c : plainChar) {
            encryptedText += String.valueOf(8964 * c) + "*";
        }

        StringBuilder modified = new StringBuilder(encryptedText);

        for (int i = 0; i < encryptedText.length()*1.5; i+=3) {
            modified.insert(i, shitThing((int) (Math.random()*10)));
        }
        encryptedText = modified.toString();
        return encryptedText;
    }

    public static char shitThing(int number) {
        switch (number) {
            case 0: return '@'; 
            case 2: return '-'; 
            case 3: return '+'; 
            case 4: return '#'; 
            case 5: return '^'; 
            case 6: return '$'; 
            case 7: return '`'; 
            case 8: return '{'; 
            case 9: return '}';
            case 1: return '['; 
        }
        return '|';
    }

    public static String decrypt(String encryptedText) {
        char[] encrptChar = encryptedText.toCharArray();
        String[] letters = new String[5];
        for (int i = 0; i < letters.length; i++) {
            letters[i] = ""; // Initialize each element
        }
        int i = 0;
        for (char c : encrptChar) {
            if (Character.isDigit(c)) {
                letters[i] += c;
            } else if (c == '*') {
                i++;
            }
        }
        StringBuilder plainText = new StringBuilder();
        for (i = 0; i < 5; i++) {
            plainText.append(Character.toChars(Integer.parseInt(letters[i]) / 8964));
        }
        String plain = plainText.toString().toUpperCase();
        System.out.println(plainText + " " + letters[0]);
        return plain;
    }
}