package Java.Data;

import java.util.ArrayList;

public class Shop {
    private ArrayList<Toys> productList;
    private ArrayList<Toys> lotteryList;

    public Shop() {
        productList = new ArrayList<>();
        lotteryList = new ArrayList<>();
    }

    public void addProduct(Toys toy) {
        productList.add(toy);
    }

    public ArrayList<Toys> getProductList() {
        return productList;
    }

    public Toys getToys(int id) {
        return productList.get(id);
    }

    public void addLotteryProduct(int id) {
        // Находим игрушку с заданным id в productList
        Toys selectedToy = null;
        int selectedToyIndex = -1;
        for (int i = 0; i < productList.size(); i++) {
            Toys toy = productList.get(i);
            if (toy.getId() == id) {
                selectedToy = toy;
                selectedToyIndex = i;
                break;
            }
        }

        if (selectedToy != null && selectedToy.getCount() > 0) {
            boolean isLotteryToyFound = false;
            // Проверяем, есть ли игрушка с заданным id в lotteryList
            for (Toys lotteryToy : lotteryList) {
                if (lotteryToy.getId() == id) {
                    // Если игрушка уже есть в lotteryList, увеличиваем ее count на 1
                    lotteryToy.setCount(lotteryToy.getCount() + 1);
                    isLotteryToyFound = true;
                    break;
                }
            }

            if (!isLotteryToyFound) {
                // Если игрушки с таким id нет в lotteryList, создаем новый объект класса Toys
                // с count равным 1 и добавляем его в lotteryList
                Toys lotteryToy = new Toys(selectedToy.getId(), selectedToy.getName(), 1, selectedToy.getWeight());
                lotteryList.add(lotteryToy);
            }

            // Уменьшаем количество игрушек в productList на 1
            selectedToy.setCount(selectedToy.getCount() - 1);

            // Если count стал равен 0, удаляем продукт из productList
            if (selectedToy.getCount() == 0 && selectedToyIndex != -1) {
                productList.remove(selectedToyIndex);
            }
        }
    }

    public ArrayList<Toys> getLotteryList() {
        ArrayList<Toys> newLotteryList = new ArrayList<>();
        for (int i = 0; i < lotteryList.size(); i++) {
            if(lotteryList.get(i).getCount() != 0){
                newLotteryList.add(lotteryList.get(i));
            }
        }
        return newLotteryList;
        // return lotteryList;
    }
}
