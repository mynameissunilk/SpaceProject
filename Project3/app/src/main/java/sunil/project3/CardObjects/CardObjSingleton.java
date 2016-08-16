package sunil.project3.CardObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 8/16/2016.
 */
public class CardObjSingleton {

    public List<CardObject> masterArray = new ArrayList<>();

    public static CardObjSingleton instance;

    private CardObjSingleton(){}

    public static CardObjSingleton getInstance(){
        if(instance == null){
            instance = new CardObjSingleton();
        }
        return instance;
    }

    public void addToMasterList(List<CardObject> cardObjectArrayList) {
        masterArray.addAll(cardObjectArrayList);
    }

    public List<CardObject> getMasterList(){
        return masterArray;
    }
}
