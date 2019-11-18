# Hello-World
//my first programming project/s
//Year 1 of the day of our Computer Lord and Savior!
package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.setOut;

public class MiddleCalculator {
    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        int coord1, coord2, position;

        out.print("First Coord: ");
        coord1 = keyboard.nextInt();
        out.print("Second Coord: ");
        coord2 = keyboard.nextInt();

        position = coord1 > coord2 ? (((coord1 - coord2) / 2) + coord2): (((coord2 - coord1) / 2) + coord1);
        out.println("pos: " + position);

//        if (coord1 > coord2) {
//            expression1 = (coord1 - coord2) / 2;
//            expression2 = coord2 + expression1;
//            out.println("pos: " + expression2);
//        } else {
//            expression2 = (coord2 - coord1) / 2;
//            expression1 = coord1 + expression2;
//            out.println(expression1);;
//        }
//
        keyboard.close();
        }
    }
