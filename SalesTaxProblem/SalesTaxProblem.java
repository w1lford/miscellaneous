package com.company;

import java.util.HashMap;
import java.util.ArrayList;

class SalesTaxProblem {
    static class SalesTaxCalculator {

        private enum ItemType {
            FOOD,
            BOOK,
            MEDICAL,
            OTHER
        }

        //A hashmap of special keywords used to determine the item type.
        private HashMap<String, ItemType> keywords = new HashMap<String, ItemType>();

        private class Item {
            private	String name;
            private double price;
            private ItemType type;
            private boolean imported;

            public Item(String name, double price, boolean imported, ItemType type) {
                this.name = name;
                this.price = price;
                this.type = ItemType.OTHER;
                this.imported = imported;
                this.type = type;
            }

            public double getPrice() { return price; }
            public ItemType getType() { return type; }
            public boolean isImported() {return imported; }
            public String getName() { return name; }

            @Override
            public String toString() {
                return "Name: " + name + ", " + "Price: " + price + ", " + "Imported: " + imported + ", " + "Type: " + type;
            }
        }

        public SalesTaxCalculator(String input) {
            //register keyword associations
            System.out.println("INPUT: ");
            System.out.println(input);
            keywords.put("chocolate", ItemType.FOOD);
            keywords.put("chocolates", ItemType.FOOD);
            keywords.put("book", ItemType.BOOK);
            keywords.put("pills", ItemType.MEDICAL);

            //parse input data
            ArrayList<Item> data = parseInput(input);

            //print receipt to console
            System.out.println("OUTPUT: ");
            printReceipt(data);

        }

        /**
         * Processes the receipt data and calculates the taxes for each item.
         * @param inputData An ArrayList of items containing the data to be processed.
         */
        public void printReceipt(ArrayList<Item> inputData) {
            double totalSalesTax = 0;
            double totalPrice = 0;
            for(int i = 0; i <= inputData.size()-1; i++) {
                Item item = inputData.get(i);
                Double tax = 0.0;
                //if this is not food books or medical stuff
                if(item.getType() == ItemType.OTHER) {
                    tax += 0.10 * item.getPrice();
                }

                if(item.isImported()){
                    tax += 0.05 * item.getPrice();
                }
                totalSalesTax += tax;
                totalPrice += item.getPrice() + tax;
                System.out.println(item.getName() + ": " + round(item.getPrice() + tax));
            }
            System.out.println("Sales Taxes: " + round(totalSalesTax));
            System.out.println("Total: " + round(totalPrice));
        }

        /**
         * Rounds a double to the nearest five cents.
         * @param number The number to be rounded.
         * @return The rounded number.
         */
        private Double round(Double number) {
            return ((double) (long) (number * 20 + 0.5)) / 20;
        }

        /**
         * Reads in string user input data and outputs the results in a clean list.
         * @param str The input string to be parsed.
         * @return An ArrayList of items containing information about each entry.
         */
        public ArrayList<Item> parseInput(String str) {

            ArrayList<Item> items = new ArrayList<Item>();

            String[] itemSplits = str.split("\n");

            for (String s: itemSplits) {
                String[] spaceSplits = s.split(" ");
                String itemName = "";
                boolean imported = false;
                ItemType type = ItemType.OTHER;

                for(int i = 1; i <= spaceSplits.length - 3; i++) {
                    itemName += spaceSplits[i] + " ";
                    if(spaceSplits[i].equals("Imported")) {
                        imported = true;
                    }
                    //if this is a match
                    if(keywords.get(spaceSplits[i].toLowerCase()) != null) {
                        type = keywords.get(spaceSplits[i].toLowerCase());
                    }
                }
                double price = Double.parseDouble(spaceSplits[spaceSplits.length-1]);
                Item i = new Item(itemName,price, imported, type);
                items.add(i);
            }
            return items;
        }

    }


    public static void main(String[] args) {
        String input1 = "1 Book at 12.49\n1 Book at 12.49\n1 Music CD at 14.99\n1 Chocolate bar at 0.85";
        String input2 = "1 Imported box of chocolates at 10.00\n1 Imported bottle of perfume at 47.50";
        String input3 = "1 Imported bottle of perfume at 27.99\n1 Bottle of perfume at 18.99\n1 Packet of headache pills at 9.75\n1 Imported box of chocolates at 11.25\n1 Imported box of chocolates at 11.25";
        SalesTaxCalculator s1 = new SalesTaxCalculator(input1);
        SalesTaxCalculator s2 = new SalesTaxCalculator(input2);
        SalesTaxCalculator s3 = new SalesTaxCalculator(input3);
    }

}
