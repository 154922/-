package com.inmind.test_03;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartSytem {
    //保存所有的商品list
    private static ArrayList<Product> products = new ArrayList<>();
    //保存购物车map
    private static HashMap<Product,Integer> carts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //初始化商品
        initData();

        //提示
        System.out.println("===== 简易购物系统 =====");

        showMainMenu();
    }

    private static void showMainMenu() {
        while (true) {
            boolean flag = false;
            System.out.println();
            System.out.println();
            System.out.println("===== 主菜单 =====");
            System.out.println("1. 浏览所有商品");
            System.out.println("2. 搜索商品");
            System.out.println("3. 查看购物车");
            System.out.println("4. 添加商品到购物车");
            System.out.println("5. 修改购物车商品数量");
            System.out.println("6. 清空购物车");
            System.out.println("7. 退出");
            System.out.println("8. 按分类去查看商品？？？");
            System.out.println("请选择:");
            int choice = sc.nextInt();
            if (choice<1||choice>7) {
                System.out.println("无效的选项");
                continue;
            }

            switch (choice) {
                case 1: browsProducts(); break;
                case 2: searchProduct(); break;
                case 3: showCarts(); break;
                case 4: addCart(); break;
                case 5: putCart(); break;
                case 6: clearCart(); break;
                case 7:
                    System.out.println("谢谢使用！！再见");
                    flag = true;
                    break;
            }

            if (flag) {
                break;//结束外面的死循环
            }
        }
    }

    private static void clearCart() {
        carts.clear();
        System.out.println("购物车已清空");
    }

    private static void putCart() {
        System.out.println("请输入要修改的商品ID:");
        sc.nextLine();
        String pId = sc.nextLine();
        //先判断下是否有该商品
        Product product = null;
        for (Product p : carts.keySet()) {
            if (p.getId().equals(pId)) {
                product = p;
            }
        }
        //如果有则保存数量，如没有则return
        if (product == null) {
            System.out.println("购物车中未找到该商品");
            return;
        }

        System.out.println("请输入新的数量:");
        int quantity = sc.nextInt();

        carts.put(product,quantity);
        System.out.println("数量已更新");
    }

    private static void addCart() {
        System.out.println("请输入商品ID: ");
        sc.nextLine();
        String pId = sc.nextLine();
        //先判断下是否有该商品
        Product product = null;
        for (Product p : products) {
            if (p.getId().equals(pId)) {
                product = p;
            }
        }
        //如果有则保存数量，如没有则return
        if (product == null) {
            System.out.println("未找到该商品");
            return;
        }

        System.out.println("请输入购买数量:");
        int quantity = sc.nextInt();

        //添加到购物车，product：数量
        if (carts.containsKey(product)) {
            carts.put(product, carts.get(product) + quantity);
        } else {
            carts.put(product, quantity);
        }
        System.out.println("已加入购物车");

    }

    private static void showCarts() {
        System.out.println("===== 购物车 =====");
        if (carts.isEmpty()) {
            System.out.println("购物车是空的！！");
            return;
        }

        int total = 0;//总价
        for (Map.Entry<Product, Integer> entry : carts.entrySet()) {
            Product product = entry.getKey();
            Integer count = entry.getValue();
            int sum  = product.getPrice()*count;
            System.out.println(product.getName()+", 数量: "+count+", 小计: ￥"+sum);
            total += sum;
        }

        System.out.println("购物车总计: ￥"+total);
    }

    private static void searchProduct() {
        System.out.println("请输入商品名称关键字:");
        sc.nextLine();
        String search = sc.nextLine();
        ArrayList<Product> searchLists = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().contains(search)) {
                searchLists.add(product);
            }
        }

        System.out.println("===== 搜索结果 =====");
        if (searchLists.size() == 0) {
            System.out.println("没有找到匹配的商品");
        } else {
            for (Product product : searchLists) {
                System.out.println(product);
            }
        }
    }

    private static void browsProducts() {
        System.out.println("===== 所有商品 =====");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }

    private static void method() {

    }

    private static void initData() {
        products.add(new Product("P01", "笔记本电脑", 5999, "电子产品", 10));
        products.add(new Product("P02", "机械键盘", 299, "电脑配件", 20));
        products.add(new Product("P03", "无线鼠标", 129, "电脑配件", 30));
        products.add(new Product("P04", "蓝牙耳机", 799, "音频设备", 15));
        products.add(new Product("P05", "智能手机", 3999, "电子产品", 25));
    }
}
