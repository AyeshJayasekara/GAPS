package com.ayeshj.gapstar;

import java.util.List;
import java.util.Random;

public interface DemonstrationHelper {

    default int getRandomNumber(int maxRange){
        int randInt = new Random().nextInt(maxRange);
        return randInt == 0 ? 1 : randInt;
    }

    default  <T> T getRandomItem(List<T> itemList){
        return itemList.get(getRandomNumber(itemList.size()));
    }

}
