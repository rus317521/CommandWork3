import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Хлеб", "Молоко", "Сахар", "Яблоки", "Чай"};
        int[] prices = {40, 80, 70, 110, 300};
        int[] shopCart = new int[products.length]; // записали в какой ячейке сколько штук лежит
        int sumProducts = 0;

        String[] saleProducts = {"Мороженое Филёвское", "Чипсы Lays", "Шоколад KitKat"};
        int[] salePrices = {60, 180, 150};
        int[] saleShopCart = new int[saleProducts.length];
        int saleSumProducts = 0;

        int productNumber; // номер выбранного продукта
        int productCount; // количество продукта
        int saleProductNumber; // номер акционного продукта
        int saleProductCount; // количество акционного продукта

        while (true) {
            System.out.println("Введите номер продукта и его количество или наберите end для выхода:");
            for (int i = 0; i < products.length; i++) {
                System.out.println(i + 1 + "." + " " + products[i] + " " + prices[i] + " " + "руб/шт");
            }
            System.out.println("Список товаров по акции 3 = 2");
            for (int j = 0; j < saleProducts.length; j++) {
                System.out.println((j + products.length + 1) + "." + " " + saleProducts[j] + " " + salePrices[j] + " " + " руб/шт");
            }

            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            String[] count = input.split(" ");

            try {
                productNumber = Integer.parseInt(count[0]) - 1; // определили номер продукта
                productCount = Integer.parseInt(count[1]); // определили количество штук продукта
            } catch (NumberFormatException e) {
                System.out.println("ОШИБКА!");
                System.out.println("Введите два числа!");
                System.out.println(" ");
                continue;
            }

            if (count.length != 2) {
                System.out.println("Введено не 2 значения!");
                System.out.println(" ");
                continue;
            }

            if (productNumber < 0 || productNumber > products.length + saleProducts.length) {
                System.out.println("Введён неверный номер продукта!");
                System.out.println("Укажите верный номер продукта!");
                System.out.println(" ");
                continue;
            }

            if (productNumber < products.length) {
                shopCart[productNumber] += productCount; // посчитали итоговое количество штук конкретного продукта
                int sum = productCount * prices[productNumber]; // посчитали цену конкретной позиции
                sumProducts += sum; // посчитали общую сумму покупок
            } else {
                saleProductNumber = productNumber - products.length;
                saleProductCount = productCount;
                saleShopCart[saleProductNumber] += saleProductCount;
                int saleKits = saleShopCart[saleProductNumber] / 3;
                int saleSum = salePrices[saleProductNumber] * (saleKits * 2 + saleShopCart[saleProductNumber] % 3);
                saleSumProducts += saleSum;
            }
        }

        System.out.println("Ваша корзина:");

        System.out.println("Товары без акции:");
        for (int i = 0; i < products.length; i++) {
            if (shopCart[i] != 0) {
                System.out.println(products[i] + " " + shopCart[i] + " шт, " + prices[i] + " руб/шт, " + (shopCart[i] * prices[i]) + " рублей в сумме");
            }
        }
        System.out.println("Итого " + sumProducts + " руб");

        System.out.println("Товары по акции 3 = 2: ");
        for (int i = 0; i < saleShopCart.length; i++) {
            if (saleShopCart[i] != 0) {
                System.out.println(saleProducts[i] + " " + saleShopCart[i] + " шт, " + salePrices[i] + " руб/шт, " + (saleShopCart[i] * salePrices[i]) + " рублей в сумме");
            }
        }
        System.out.println("Итого по акции: " + saleSumProducts + " руб");
        System.out.println("Итого за всё: " + (sumProducts + saleSumProducts) + " руб");
    }
}