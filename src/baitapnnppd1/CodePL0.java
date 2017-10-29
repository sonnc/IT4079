/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitapnnppd1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sonnguyen
 */
public class CodePL0 {

    String text = null;
    int start;
    int start1;
    int end;
    Token t = new Token();
    ArrayList<Token> arrToken = new ArrayList<>();
    ArrayList<String> token = new ArrayList<String>() {
        {
            add("NONE");
            add("IDENT");
            add("NUMBER");
            add("BEGIN");
            add("VAR");
            add("CALL");
            add("CONST");
            add("DO");
            add("ELSE");
            add("END");
            add("FOR");
            add("IF");
            add("ODD");
            add("PROCEDURE");
            add("PROGRAM");
            add("THEN");
            add("TO");
            add("WHILE");
            add("PLUS");
            add("MINUS");
            add("TIMES");
            add("SLASH");
            add("EQU");
            add("NEQ");
            add("LSS");
            add("LEQ");
            add("GTR");
            add("PERCENT");
            add("LPARENT");
            add("RPARENT");
            add("LBRACK");
            add("RBRACK");
            add("PERIOD");
            add("COMMA");
            add("SEMICOLON");
            add("ASSIGN");
            add("EXP");
        }
    };

    public void docFile() {
        try {
            String input = "vidu.txt";
            FileInputStream fis = new FileInputStream(new File(input));
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = br.readLine()) != null) {
                if (line != null && !line.isEmpty()) {
                    if (text == null) {
                        text = line;
                    } else {
                        text = text + "\n" + line;
                    }
                }
            }
//            System.out.println("" + text);
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void xuLy() {
        String substr;
        for (int i = 0; i < text.length(); i++) {
            if ((Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == ' ') || (Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == '\n')) {
                for (int j = i; j <= i; j--) {
                    if ((text.charAt(i + 1) == ' ' || text.charAt(i + 1) == '+' || text.charAt(i + 1) == '*') && (text.charAt(j) == ' ' || text.charAt(j) == '=') && (text.charAt(j) == '\n')) {
                        substr = text.substring(j + 1, i + 1);
                        System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                        break;
                    } else if (j == 0) {
                        break;
                    } else if (Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == ' ' && text.charAt(i - 1) == ' ') {
                        substr = text.substring(i, i + 1);
                        System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                        break;
                    } else if ((text.substring(i - 2, i + 1)).equals("ALL")) {
                        System.out.println(t.getCall());
                        break;
                    } else if ((text.substring(i - 2, i + 1)).equals(t.getEnd())) {
                        System.out.println(t.getEnd());
                        break;
                    }
                }
                if (Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == '\n' && text.charAt(i - 1) == ' ') {
                    substr = text.substring(i, i + 1);
                    System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                }
                substr = text.substring(start, i + 1);
                for (int j = 0; j < token.size(); j++) {

                    if ((token.get(j).equals(substr))) {
                        System.out.println("" + substr);
                        break;
                    } else {
                        try {
                            String substr2 = substr.substring(0, substr.length());
                            for (int k = 0; k < token.size(); k++) {
                                if ((token.get(k).equals(substr2))) {
                                    System.out.println("" + substr);
                                    substr = null;
                                    start = start + 6;
                                    start1 = start + 6;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            } else if (text.charAt(i) == ' ') {
                // tiếp tục đọc ký tự tiếp theo
            } else if (Character.isLetter(text.charAt(i)) && text.charAt(i + 1) == ',') {
                start = i;
                substr = text.substring(start, i + 1);
                end = i - 1;
                System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                System.out.println(t.getComma());
            } else if (Character.isLetter(text.charAt(i)) && (text.charAt(i + 1) == ';')) {
                for (int j = i; j <= i; j--) {
                    if (text.charAt(j) == ' ') {
                        substr = text.substring(j + 1, i + 1);
                        end = i - 1;
                        start = i + 3;
                        System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                        System.out.println(t.getSemicolon());
                        break;

                    }
                    if (text.charAt(j) == '\n') {
                        substr = text.substring(j + 1, i + 1);
                        for (int k = 0; k < token.size(); k++) {
                            if (token.get(k).equals(substr)) {
                                end = i - 1;
                                start = i + 3;
                                System.out.println(t.getEnd());
                                System.out.println(t.getSemicolon());
                                break;
                            }
                        }
                        break;
                    }
                }
            } else if ((Character.isLetterOrDigit(text.charAt(i))) && (text.charAt(i + 1) == ';')) {
                for (int j = i; j <= i; j--) {
                    if (text.charAt(j) == ' ') {
                        substr = text.substring(j + 1, i + 1);
                        end = i - 1;
                        start = i + 6;
                        System.out.println(t.getNumber() + "(" + substr + ")");
                        System.out.println(t.getSemicolon());
                        break;
                    }
                }
            } else if (text.charAt(i) == '.') {
                for (int j = i; j <= i; j--) {
                    if (Character.isLetter(text.charAt(j)) && text.charAt(j - 1) == '\n') {
                        System.out.println(t.getEnd());
                        System.out.println(t.getPeriod());
                        break;
                    }
                }
            } else if (text.charAt(i) == ':' & text.charAt(i + 1) == '=') {
                for (int j = text.indexOf(':'); j <= text.indexOf(':'); j--) {
                    if (text.charAt(j) == ' ') {
                        substr = text.substring(text.charAt(j) + 7, i);
                        System.out.println(t.getIdent() + "(" + substr.toUpperCase() + ")");
                        System.out.println(t.getAssign());
                        break;
                    }
                    if (text.charAt(i) == ':' && text.charAt(i + 1) == '=' && text.charAt(i - 1) == ' ') {
                        System.out.println(t.getAssign());
                        break;
                    }
                }
            } else if (text.charAt(i) == '*') {
                System.out.println(t.getTimes());
            } else if (text.charAt(i) == '<' && text.charAt(i + 1) == '=') {
                System.out.println(t.getLeq());
            } else if (text.charAt(i) == '=') {
                for (int j = i; j >= i; j++) {
                    if (text.charAt(j) == ' ' && !Character.isLetter(text.charAt(j + 1))) {
                        substr = text.substring(j + 1, j + 3);

                        try {
                            int check = Integer.parseInt(substr);
                            System.out.println(t.getNumber() + "(" + substr + ")");
                            start = i + 5;
                            break;
                        } catch (Exception e) {
                            break;
                        }
                    }
                }
            } else if (text.charAt(i) == '+' && text.charAt(i + 1) == ' ') {
                substr = text.substring(i + 2, i + 3);
                System.out.println(t.getNumber() + "(" + substr + ")");
            } else if (text.charAt(i) == '!') {
                System.out.println(t.getExp());
            } else if (text.charAt(i) == '+') {
                System.out.println(t.getPlus());
            } else if (text.charAt(i) == '/') {
                System.out.println(t.getSlash());
            } else if (text.charAt(i) == '%') {
                System.out.println(t.getPercent());
            } else if (text.charAt(i) == ']') {
                System.out.println(t.getRbrack());
            } else if (text.charAt(i) == '[') {
                System.out.println(t.getLbrack());
            } else if (text.charAt(i) == ')') {
                System.out.println(t.getRparent());
            } else if (text.charAt(i) == '(') {
                System.out.println(t.getLparent());
            }
        }
    }
}
