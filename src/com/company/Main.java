package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //ввод данных
        int shuffleGroupSize = 1;
        int shuffleSessions = 1;
        int lengthOfArray = 2;

        System.out.print("Введите длину массива -> ");
        lengthOfArray = scanner.nextInt();

        System.out.print("Введите количество элементов в группе -> ");
        shuffleGroupSize = scanner.nextInt();

        System.out.print("Введите количество сессий перемешивания -> ");
        shuffleSessions = scanner.nextInt();



        //проверка на кратность, потому что массив должен делиться на одинаковые группы
        //печать исходного массива, перемешивание, печать выходного массива
        if(shuffleGroupSize > 0 && (lengthOfArray % shuffleGroupSize == 0) && shuffleSessions >= 0 && lengthOfArray > 0 && shuffleGroupSize != lengthOfArray) {
            int[] array = new int[lengthOfArray];

            fillArray(array, shuffleGroupSize);

            System.out.println("До:");
            printArray(array, shuffleGroupSize);

            shuffleArray(array, shuffleGroupSize, shuffleSessions);

            System.out.println("\nПосле:");
            printArray(array, shuffleGroupSize);
        }
        else
            System.out.println("Длина массива должна быть кратной количеству элементов в группе для перемешивания, а все значения положительные!");
    }

    //заполняю массив специально такими значениями,
    // чтоб при проверке было легче проверять перемешивание по конкретным группам
    public static void fillArray(int[] array, int blockSize) {
        int num = 0;
        for (int i = 0; i < array.length ; i++) {
            if (i % blockSize == 0 && i != 0)
                num++;
            array[i] = num;
        }
    }

    //массив специально выводится по группам, для наглядной демострации перемешивания
    public static void printArray(int[] array, int blockSize) {
        for (int i = 0; i < array.length; i++) {
            if (i % blockSize == 0 && i != 0) {
                System.out.print("\t\t" + array[i] + " ");
            } else {
                System.out.print(" " + array[i] + " ");
            }
        }
    }

    //перемешивание рандомных блоков с соседними определенное количество раз
    public static void shuffleArray(int[] array, int blockSize, int shuffleSessions) {
        int shuffleCounter = 0;
        while (shuffleCounter < shuffleSessions) {
            int shuffleRandomizeBlockPosition = (int) ((Math.random() * (array.length / blockSize)) - 1);
            for (int i = (shuffleRandomizeBlockPosition * blockSize); i < (shuffleRandomizeBlockPosition * blockSize) + blockSize; i++) {
                int memoryElement = array[i + blockSize];
                array[i + blockSize] = array[i];
                array[i] = memoryElement;
            }
            shuffleCounter++;
        }
    }
}
