package Lesson31.shop_jdbc.services.impl;

import Lesson31.shop_jdbc.models.*;
import Lesson31.shop_jdbc.services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class SellOperationServiceImpl implements SellOperationService {
    Scanner scanner = new Scanner(System.in);
    ShopService shopService = new ShopServiceImpl();
    SellerService sellerService = new SellerServiceImpl();
    ProductService productService = new ProductServiceImpl();
    ReceiptService receiptService = new ReceiptServiceImpl();
    ProductReceiptService productReceiptService = new ProductReceiptServiceImpl();
    @Override
    public void sellProducts() {
        System.out.println(shopService.getShops());
        System.out.print("Введите Id магазина в котором хотите совершить покупку: ");
        int shopId = Integer.parseInt(scanner.nextLine());
        System.out.println(sellerService.findSellersByShopId(shopId));
        System.out.print("Введите Id обслуживающего сотрудника: ");
        int sellerId = Integer.parseInt(scanner.nextLine());
        ArrayList<ProductCount> myProducts = new ArrayList<>();
        while (true){
            System.out.println(productService.getProducts());
            System.out.print("Выберите продукт (укажите ID): ");
            int productId = Integer.parseInt(scanner.nextLine());
            System.out.print("Введите количество: ");
            int productCount = Integer.parseInt(scanner.nextLine());
            Product product = productService.findProductById(productId);
            ProductCount productCounts = new ProductCount(product, productCount);
            myProducts.add(productCounts);
            System.out.print("Желаете ещё продукт? (1 - Да, 0 - Нет): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                continue;
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Ошибка");
            }
        }
        receiptService.createReceipt(new Receipt(new Date(), sellerId));
        printCheck(myProducts, shopId, sellerId);
    }

    @Override
    public void printCheck(ArrayList<ProductCount> products, int shopId, int sellerId) {
        System.out.println();
        System.out.println("          Ваш чек");
        Shop shop = shopService.findShopById(shopId);
        System.out.println("Магазин: " + shop.getName());
        Seller seller = sellerService.findSellerById(sellerId);
        System.out.println("Продавец: " + seller.getName());
        System.out.println("Продукты: ");
        double totalSum = 0;
        for (int i = 0; i < products.size(); i++) {
            Product prd = products.get(i).getProduct();
            System.out.println("  " + (i+1) + ") " + prd.getName() + ", цена: " + prd.getPrice() +
                    ", кол-во: " + products.get(i).getCount());
            totalSum += (prd.getPrice() * products.get(i).getCount());
        }
        System.out.println("Итого к оплате: " + totalSum);
        System.out.println("Спасибо за покупку!!!");
        System.out.println();
        insertCustomerProducts(products);
    }

    @Override
    public void insertCustomerProducts(ArrayList<ProductCount> products) {
        int myReceiptId = receiptService.getLastReceiptId();
        for (int i = 0; i < products.size(); i++) {
            ProductReceipt productReceipt = new ProductReceipt(myReceiptId, products.get(i).getProduct().getId());
            int counts = products.get(i).getCount();
            while (counts > 0){
                productReceiptService.createReceiptProduct(productReceipt);
                counts--;
            }
        }
    }
}
