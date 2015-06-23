/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blackboard;

import java.util.ArrayList;

/**
 *
 * @author LanAnh
 */
public class ListDataModel {

    public ArrayList listData;

    public ListDataModel() {
        listData = new ArrayList();
    }

    public void add(DataModel p) {
        listData.add(p);

    }

    public void display() {
        for (int i = 0; i < listData.size(); i++) {
            System.out.println(listData.toString());
        }
    }

    public void remove(DataModel p) {
        listData.remove(p);
    }

// sort by dataModel.getColor();
    public ListDataModel sort(ListDataModel listData) {
        ListDataModel newListData = new ListDataModel();
        for (int i = 0; i < listData.listData.size() - 1; i++) {
            DataModel data_m1 = (DataModel) listData.listData.get(i);
            if (!data_m1.getColor().equals("white")) {
                newListData.add(data_m1);
            }
        }
        for (int i = 0; i < listData.listData.size() - 1; i++) {
            DataModel data_m1 = (DataModel) listData.listData.get(i);
            if (data_m1.getColor().equals("white")) {
                newListData.add(data_m1);
            }
        }
        return newListData;
    }

}
