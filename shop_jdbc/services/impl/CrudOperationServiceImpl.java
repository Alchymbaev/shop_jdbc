package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.models.Product;
import Lesson31.shop_jdbc.models.Seller;
import Lesson31.shop_jdbc.models.Shop;
import Lesson31.shop_jdbc.services.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class CrudOperationServiceImpl implements CrudOperationService {
    Scanner scanner = new Scanner(System.in);
    ShopService shopService = new ShopServiceImpl();
    SellerService sellerService = new SellerServiceImpl();
    ReceiptService receiptService = new ReceiptServiceImpl();
    ProductService productService = new ProductServiceImpl();
    ProductReceiptService productReceiptService = new ProductReceiptServiceImpl();

    @Override
    public void doChanges() {
        System.out.print("В какой таблице внести изменения?\n " +
                "1 - tb_shop\n 2 - tb_seller\n 3 - tb_receipt\n 4 - tb_product\n 5 - tb_product_receipt\n Таблица: ");
        int table = Integer.parseInt(scanner.nextLine());
        System.out.print("Какую операцию выполнить?\n " +
                "1 - добавить\n 2 - удалить\n 3 - изменить\n 4 - показать содержимое\n Операция: ");
        int operation = Integer.parseInt(scanner.nextLine());
        String errorOperation = "Вы ввели неверный номер операции";
        switch (table){
            case 1:
                if (operation == 1){
                    System.out.print("Введите название магазина: ");
                    Shop shop = new Shop(scanner.nextLine(), 1);
                    shopService.createShop(shop);
                } else if (operation == 2){
                    System.out.println(shopService.getShops());
                    System.out.print("Введите id магазина который хотите удалить: ");
                    shopService.deleteShop(Integer.parseInt(scanner.nextLine()));
                } else if (operation == 3){
                    System.out.println(shopService.getShops());
                    System.out.print("Введите id магазина который хотите изменить: ");
                    int shopId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Название магазина: ");
                    String shopName = scanner.nextLine();
                    System.out.print("Активен (1 - активный, 0 - неактивный): ");
                    int shopActive = Integer.parseInt(scanner.nextLine());
                    shopService.updateShop(shopId, shopName, shopActive);
                } else if (operation == 4) {
                    System.out.println(shopService.getShops());
                } else {
                    System.out.println(errorOperation);
                }
                break;
            case 2:
                if (operation == 1){
                    System.out.print("Введите имя сотрудника: ");
                    String sellerName = scanner.nextLine();
                    System.out.print("Введите возраст сотрудника: ");
                    int sellerAge = Integer.parseInt(scanner.nextLine());
                    System.out.println(shopService.getShops());
                    System.out.print("Введите id магазина сотрудника: ");
                    int sellerShopId = Integer.parseInt(scanner.nextLine());
                    Seller seller = new Seller(sellerName, sellerAge, 1, sellerShopId);
                    sellerService.createSeller(seller);
                } else if (operation == 2){
                    System.out.println(sellerService.getSellers());
                    System.out.print("Введите id сотрудника которого хотите удалить: ");
                    sellerService.deleteSeller(Integer.parseInt(scanner.nextLine()));
                } else if (operation == 3){
                    System.out.println(sellerService.getSellers());
                    System.out.print("Введите id сотрудника которого хотите изменить: ");
                    int sellerId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Имя сотрудника: ");
                    String sellerName = scanner.nextLine();
                    System.out.print("Возраст: ");
                    int sellerAge = Integer.parseInt(scanner.nextLine());
                    System.out.print("Активен (1 - активный, 0 - неактивный): ");
                    int sellerActive = Integer.parseInt(scanner.nextLine());
                    System.out.println(shopService.getShops());
                    System.out.print("Введите id магазина сотрудника: ");
                    int sellerShopId = Integer.parseInt(scanner.nextLine());
                    sellerService.updateSeller(sellerId, sellerName, sellerAge, sellerActive, sellerShopId);
                } else if (operation == 4) {
                    System.out.println(sellerService.getSellers());
                } else {
                    System.out.println(errorOperation);
                }
                break;
            case 3:
                if (operation == 1){
                    System.out.println("Чек можно добавить только в категории 'Покупка'");
                } else if (operation == 2){
                    System.out.println(receiptService.getReceipts());
                    System.out.print("Введите id чека который хотите удалить: ");
                    receiptService.deleteReceipt(Integer.parseInt(scanner.nextLine()));
                } else if (operation == 3){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    System.out.println(receiptService.getReceipts());
                    System.out.print("Введите id чека который хотите изменить: ");
                    int receiptId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Дата чека: ");
                    Date receiptDate = null;
                    try {
                        receiptDate = sdf.parse(scanner.nextLine());
                    } catch (ParseException e) {
                        throw new RuntimeException("Неверно введена дата");
                    }

                    System.out.println(sellerService.getSellers());
                    System.out.print("Введите id сотрудника чека: ");
                    int receiptSellerId = Integer.parseInt(scanner.nextLine());
                    receiptService.updateReceipt(receiptId, receiptDate, receiptSellerId);
                } else if (operation == 4) {
                    System.out.println(receiptService.getReceipts());
                } else {
                    System.out.println(errorOperation);
                }
                break;
            case 4:
                if (operation == 1){
                    System.out.print("Введите название продукта: ");
                    String productName = scanner.nextLine();
                    System.out.print("Введите цену продукта: ");
                    Double productPrice = Double.parseDouble(scanner.nextLine());
                    Product product = new Product(productName, productPrice);
                    productService.createProduct(product);
                } else if (operation == 2){
                    System.out.println(productService.getProducts());
                    System.out.print("Введите id продукта который хотите удалить: ");
                    productService.deleteProduct(Integer.parseInt(scanner.nextLine()));
                } else if (operation == 3){
                    System.out.println(productService.getProducts());
                    System.out.print("Введите id продукта который хотите изменить: ");
                    int productId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Название продукта: ");
                    String productName = scanner.nextLine();
                    System.out.print("Цена продукта: ");
                    double productPrice = Double.parseDouble(scanner.nextLine());
                    productService.updateProduct(productId, productName, productPrice);
                } else if (operation == 4) {
                    System.out.println(productService.getProducts());
                } else {
                    System.out.println(errorOperation);
                }
                break;
            case 5:
                if (operation == 1){
                    System.out.println("Связь чека и продукта создаётся в категории 'Покупка' ");
                } else if (operation == 2){
                    System.out.println(productReceiptService.getReceiptProducts());
                    System.out.print("Введите id связи чек-продукт которую хотите удалить: ");
                    productReceiptService.deleteReceiptProduct(Integer.parseInt(scanner.nextLine()));
                } else if (operation == 3){
                    System.out.println(receiptService.getReceipts());
                    System.out.println(productService.getProducts());
                    System.out.println(productReceiptService.getReceiptProducts());
                    System.out.print("Введите id связи которую хотите изменить: ");
                    int productReceiptId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Id чека: ");
                    int receiptId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Id продукта: ");
                    int productId = Integer.parseInt(scanner.nextLine());
                    productReceiptService.updateReceiptProduct(productReceiptId, receiptId, productId);
                } else if (operation == 4) {
                    System.out.println(productReceiptService.getReceiptProducts());
                } else {
                    System.out.println(errorOperation);
                }
                break;
            default:
                System.out.println("Вы ввели неверный номер таблицы");
        }
    }
}
