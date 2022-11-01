package Lesson31.shop_jdbc;

import Lesson31.shop_jdbc.models.*;
import Lesson31.shop_jdbc.services.*;
import Lesson31.shop_jdbc.services.impl.*;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать!!!");
        System.out.print("Выберите категорию (1 - изменение базы, 2 - покупка): ");
        int category = Integer.parseInt(scanner.nextLine());
        if (category == 1){
            CrudOperationService crudOperationService = new CrudOperationServiceImpl();
            crudOperationService.doChanges();
        } else if (category == 2) {
            SellOperationService sellOperationService = new SellOperationServiceImpl();
            sellOperationService.sellProducts();
        } else {
            System.out.println("Ошибка неверная категория");
        }
    }
}
